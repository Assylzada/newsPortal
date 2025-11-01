package com.example.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newsportal.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
