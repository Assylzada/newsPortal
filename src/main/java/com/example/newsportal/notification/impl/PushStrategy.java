package com.example.newsportal.notification.impl;

import org.springframework.stereotype.Component;

import com.example.newsportal.notification.NotificationStrategy;
import com.example.newsportal.notification.NotificationResult;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Article;
import com.example.newsportal.notification.adapter.PushAdapter;

@Component("PUSH")
public class PushStrategy implements NotificationStrategy {

    private final PushAdapter adapter;

    public PushStrategy(PushAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public NotificationResult notify(Subscriber subscriber, Article article) {
        String payload = String.format("{\"title\": \"%s\", \"id\": %d}", article.getTitle(), article.getId());
        boolean ok = adapter.sendPush(subscriber.getContact(), payload);
        return new NotificationResult(ok, "PUSH", ok ? "sent" : "failed");
    }
}
