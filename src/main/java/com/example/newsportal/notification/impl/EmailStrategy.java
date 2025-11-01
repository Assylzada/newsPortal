package com.example.newsportal.notification.impl;

import org.springframework.stereotype.Component;

import com.example.newsportal.notification.NotificationStrategy;
import com.example.newsportal.notification.NotificationResult;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Article;
import com.example.newsportal.notification.adapter.EmailAdapter;

@Component("EMAIL")
public class EmailStrategy implements NotificationStrategy {

    private final EmailAdapter adapter;

    public EmailStrategy(EmailAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public NotificationResult notify(Subscriber subscriber, Article article) {
        String body = String.format("Hello %s, new article published: %s", subscriber.getName(), article.getTitle());
        boolean ok = adapter.sendEmail(subscriber.getContact(), article.getTitle(), body);
        return new NotificationResult(ok, "EMAIL", ok ? "sent" : "failed");
    }
}
