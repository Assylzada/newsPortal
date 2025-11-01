package com.example.newsportal.event;

import org.springframework.context.ApplicationEvent;

public class ArticlePublishedEvent extends ApplicationEvent {
    private final Long articleId;
    public ArticlePublishedEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
    public Long getArticleId() { return articleId; }
}
