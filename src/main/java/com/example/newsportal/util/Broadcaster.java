package com.example.newsportal.util;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Set;
import java.util.function.Consumer;

// Very small broadcaster utility for pushing messages to Vaadin UIs
public class Broadcaster {
    static final Set<Consumer<String>> listeners = new CopyOnWriteArraySet<>();

    public static void register(Consumer<String> listener) {
        listeners.add(listener);
    }

    public static void unregister(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public static void broadcast(String message) {
        for (Consumer<String> l : listeners) {
            l.accept(message);
        }
    }
}
