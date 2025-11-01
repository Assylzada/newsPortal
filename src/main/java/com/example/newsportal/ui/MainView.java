package com.example.newsportal.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.page.Push;

import com.example.newsportal.model.Article;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Channel;
import com.example.newsportal.service.ArticleService;
import com.example.newsportal.service.SubscriberService;
import com.example.newsportal.util.Broadcaster;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;

@Route("")

public class MainView extends VerticalLayout {

    private final ArticleService articleService;
    private final SubscriberService subscriberService;

    private final Grid<Article> articleGrid = new Grid<>(Article.class);
    private final Grid<Subscriber> subscriberGrid = new Grid<>(Subscriber.class);

    private final TextField titleField = new TextField("Title");
    private final TextField bodyField = new TextField("Body (short)");
    private final Button publishBtn = new Button("Publish Article");

    private Consumer<String> broadcasterListener;

    @Autowired
    public MainView(ArticleService articleService, SubscriberService subscriberService) {
        this.articleService = articleService;
        this.subscriberService = subscriberService;

        add(new H3("NewsPortal Demo — Admin/Live UI"));

        // Article composer
        HorizontalLayout compose = new HorizontalLayout();
        compose.add(titleField, bodyField, publishBtn);
        add(compose);

        publishBtn.addClickListener(e -> {
            String t = titleField.getValue().trim();
            String b = bodyField.getValue().trim();
            if (t.isEmpty()) {
                Notification.show("Enter title");
                return;
            }
            Article a = Article.builder().title(t).body(b).author("Admin").build();
            articleService.save(a);
            titleField.clear();
            bodyField.clear();
            Notification.show("Article published: " + a.getTitle());
            refreshArticles();
        });

        // grids
        articleGrid.setColumns("id", "title", "author", "publishedAt");
        subscriberGrid.setColumns("id", "name", "contact", "channel");
        refreshArticles();
        refreshSubscribers();
        HorizontalLayout grids = new HorizontalLayout(articleGrid, subscriberGrid);
        grids.setSizeFull();
        add(grids);

        // subscribe to broadcaster for live notifications
        broadcasterListener = msg -> {
            getUI().ifPresent(ui -> ui.access(() -> {
                Notification.show(msg, 4000, Notification.Position.TOP_END);
            }));
        };
        Broadcaster.register(broadcasterListener);
        addDetachListener(e -> Broadcaster.unregister(broadcasterListener));
    }

    private void refreshArticles() {
        articleGrid.setItems(articleService.listAll());
    }

    private void refreshSubscribers() {
        subscriberGrid.setItems(subscriberService.list());
    }
}
