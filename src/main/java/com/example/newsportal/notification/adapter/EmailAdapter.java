package com.example.newsportal.notification.adapter;

import org.springframework.stereotype.Component;

// Adapter pattern: wrap external email provider (simulated)
@Component
public class EmailAdapter {
    public boolean sendEmail(String email, String subject, String body) {
        System.out.println("[EmailAdapter] Sending email to " + email + ": " + subject);
        return true;
    }
}
