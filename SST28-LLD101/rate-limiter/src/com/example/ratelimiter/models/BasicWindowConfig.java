package com.example.ratelimiter.models;

import com.example.ratelimiter.interfaces.IWindowConfig;

public class BasicWindowConfig implements IWindowConfig {
    private final int maxRequests;
    private final long windowSizeInMillis;

    public BasicWindowConfig(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public int getMaxRequests() {
        return maxRequests;
    }

    @Override
    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }
}
