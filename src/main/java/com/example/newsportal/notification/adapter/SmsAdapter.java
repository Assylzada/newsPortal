package com.example.newsportal.notification.adapter;

import org.springframework.stereotype.Component;

// Adapter pattern: wrap external SMS provider (simulated)
@Component
public class SmsAdapter {
    public boolean sendSms(String phone, String text) {
        System.out.println("[SmsAdapter] Sending SMS to " + phone + ": " + text);
        return true;
    }
}
