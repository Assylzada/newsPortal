package com.example.newsportal.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.newsportal.service.NotificationService;

@Component
public class ArticlePublishedListener {

    private final NotificationService notificationService;

    public ArticlePublishedListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void handle(ArticlePublishedEvent ev) {
        notificationService.dispatchForArticle(ev.getArticleId());
    }
}
