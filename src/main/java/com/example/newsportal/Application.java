package com.example.newsportal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.newsportal.model.Article;
import com.example.newsportal.model.Subscriber;
import com.example.newsportal.model.Channel;
import com.example.newsportal.service.ArticleService;
import com.example.newsportal.service.SubscriberService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Seed a small dataset
    @Bean
    CommandLineRunner seed(ArticleService articleService, SubscriberService subscriberService) {
        return args -> {
            Article a1 = Article.builder()
                    .title("Welcome to NewsPortal Demo")
                    .body("This small demo shows real-time notifications and multiple design patterns.")
                    .author("System")
                    .build();
            articleService.save(a1);

            Article a2 = Article.builder()
                    .title("Second Article: Patterns in Action")
                    .body("Observer, Strategy, Factory, Builder, Adapter, Decorator are demonstrated.")
                    .author("System")
                    .build();
            articleService.save(a2);

            // subscribers
            subscriberService.addSubscriber(new Subscriber(null, "Kairat", "kairat@example.com", Channel.EMAIL));
            subscriberService.addSubscriber(new Subscriber(null, "Nurtas", "nurtas@example.com", Channel.SMS));
            subscriberService.addSubscriber(new Subscriber(null, "Charlie", "charlie@example.com", Channel.PUSH));
        };
    }
}
