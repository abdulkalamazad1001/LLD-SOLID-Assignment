package com.example.ratelimiter.interfaces;

public interface IWindowConfig {
    int getMaxRequests();
    long getWindowSizeInMillis();
}
