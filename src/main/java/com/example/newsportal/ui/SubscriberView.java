package com.example.newsportal.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Channel;
import com.example.newsportal.service.SubscriberService;

@Route("subscribers")
public class SubscriberView extends VerticalLayout {

    private final SubscriberService subscriberService;

    private final TextField name = new TextField("Name");
    private final TextField contact = new TextField("Contact (email/phone/token)");
    private final ComboBox<Channel> channel = new ComboBox<>("Channel", Channel.values());
    private final Button add = new Button("Add Subscriber");

    @Autowired
    public SubscriberView(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;

        add(new H3("Manage Subscribers"));
        channel.setValue(Channel.EMAIL);

        FormLayout form = new FormLayout();
        form.add(name, contact, channel, add);
        add(form);

        add.addClickListener(e -> {
            String n = name.getValue().trim();
            String c = contact.getValue().trim();
            Channel ch = channel.getValue();
            if (n.isEmpty() || c.isEmpty()) {
                Notification.show("Name and contact required");
                return;
            }
            Subscriber s = new Subscriber(null, n, c, ch);
            subscriberService.addSubscriber(s);
            Notification.show("Added " + n);
            name.clear(); contact.clear();
        });
    }
}
