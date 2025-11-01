package com.example.newsportal.notification.adapter;

import org.springframework.stereotype.Component;

// Adapter: simulated push provider
@Component
public class PushAdapter {
    public boolean sendPush(String deviceToken, String payload) {
        System.out.println("[PushAdapter] Sending PUSH to " + deviceToken + ": " + payload);
        return true;
    }
}
