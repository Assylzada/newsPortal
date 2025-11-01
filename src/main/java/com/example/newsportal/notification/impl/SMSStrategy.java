package com.example.newsportal.notification.impl;

import org.springframework.stereotype.Component;

import com.example.newsportal.notification.NotificationStrategy;
import com.example.newsportal.notification.NotificationResult;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Article;
import com.example.newsportal.notification.adapter.SmsAdapter;

@Component("SMS")
public class SMSStrategy implements NotificationStrategy {

    private final SmsAdapter adapter;

    public SMSStrategy(SmsAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public NotificationResult notify(Subscriber subscriber, Article article) {
        String text = article.getTitle() + ": " + (article.getBody()==null?"":article.getBody().substring(0, Math.min(80, article.getBody().length())));
        boolean ok = adapter.sendSms(subscriber.getContact(), text);
        return new NotificationResult(ok, "SMS", ok ? "sent" : "failed");
    }
}
