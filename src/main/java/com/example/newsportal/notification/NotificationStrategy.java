package com.example.newsportal.notification;

import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Article;

public interface NotificationStrategy {
    NotificationResult notify(Subscriber subscriber, Article article);
}
