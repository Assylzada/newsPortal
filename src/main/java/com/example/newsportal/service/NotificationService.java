package com.example.newsportal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.newsportal.model.Article;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.repository.ArticleRepository;
import com.example.newsportal.repository.SubscriberRepository;
import com.example.newsportal.notification.NotificationFactory;
import com.example.newsportal.notification.NotificationResult;
import com.example.newsportal.notification.NotificationStrategy;
import com.example.newsportal.notification.decorator.LoggingDecorator;
import com.example.newsportal.util.Broadcaster;

@Service
public class NotificationService {

    private final SubscriberRepository subscriberRepo;
    private final ArticleRepository articleRepo;
    private final NotificationFactory factory;

    public NotificationService(SubscriberRepository subscriberRepo, ArticleRepository articleRepo, NotificationFactory factory) {
        this.subscriberRepo = subscriberRepo;
        this.articleRepo = articleRepo;
        this.factory = factory;
    }

    @Transactional
    public void dispatchForArticle(Long articleId) {
        Article article = articleRepo.findById(articleId).orElseThrow();
        List<Subscriber> subs = subscriberRepo.findAll();

        subs.parallelStream().forEach(s -> {
            NotificationStrategy strat = factory.get(s.getChannel().name());
            if (strat == null) {
                System.out.println("No strategy for channel " + s.getChannel());
                return;
            }
            NotificationStrategy decorated = new LoggingDecorator(strat);
            NotificationResult res = decorated.notify(s, article);
            Broadcaster.broadcast(String.format("[%s] %s -> %s (%s)", res.channel(), s.getName(), article.getTitle(), res.detail()));
        });
    }
}
