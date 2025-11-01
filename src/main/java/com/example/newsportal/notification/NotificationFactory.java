package com.example.newsportal.notification;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    private final Map<String, NotificationStrategy> map;

    public NotificationFactory(java.util.List<NotificationStrategy> strategies) {
        this.map = strategies.stream().collect(Collectors.toMap(s -> {
            return s.getClass().getAnnotation(org.springframework.stereotype.Component.class).value();
        }, s -> s));
    }

    public NotificationStrategy get(String channelName) {
        return map.get(channelName);
    }
}
