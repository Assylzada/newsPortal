package com.example.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newsportal.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
