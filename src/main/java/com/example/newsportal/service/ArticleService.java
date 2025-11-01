package com.example.newsportal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

import com.example.newsportal.model.Article;
import com.example.newsportal.repository.ArticleRepository;
import com.example.newsportal.event.ArticlePublishedEvent;

@Service
public class ArticleService {
    private final ArticleRepository repo;
    private final ApplicationEventPublisher publisher;

    public ArticleService(ArticleRepository repo, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    public Article save(Article article) {
        Article a = repo.save(article);
        publisher.publishEvent(new ArticlePublishedEvent(this, a.getId()));
        return a;
    }

    public List<Article> listAll() {
        return repo.findAll();
    }
}
