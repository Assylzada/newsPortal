package com.example.newsportal.notification.decorator;

import com.example.newsportal.notification.NotificationStrategy;
import com.example.newsportal.notification.NotificationResult;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Article;

public class LoggingDecorator implements NotificationStrategy {

    private final NotificationStrategy inner;

    public LoggingDecorator(NotificationStrategy inner) {
        this.inner = inner;
    }

    @Override
    public NotificationResult notify(Subscriber subscriber, Article article) {
        System.out.println("[Decorator] Before sending via " + inner.getClass().getSimpleName());
        NotificationResult r = inner.notify(subscriber, article);
        System.out.println("[Decorator] Result: " + r);
        return r;
    }
}
