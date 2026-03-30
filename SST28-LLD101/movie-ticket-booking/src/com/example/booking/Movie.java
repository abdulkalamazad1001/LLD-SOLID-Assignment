package com.example.booking;

public class Movie {
    private final String id;
    private final String title;
    private final int durationInMinutes;
    private final String genre;

    public Movie(String id, String title, int duration, String genre) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = duration;
        this.genre = genre;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
}
