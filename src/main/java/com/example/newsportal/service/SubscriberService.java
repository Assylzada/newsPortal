package com.example.newsportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.newsportal.model.Subscriber;
import com.example.newsportal.repository.SubscriberRepository;

@Service
public class SubscriberService {
    private final SubscriberRepository repo;

    public SubscriberService(SubscriberRepository repo) {
        this.repo = repo;
    }

    public Subscriber addSubscriber(Subscriber s) {
        return repo.save(s);
    }

    public List<Subscriber> list() {
        return repo.findAll();
    }
}
