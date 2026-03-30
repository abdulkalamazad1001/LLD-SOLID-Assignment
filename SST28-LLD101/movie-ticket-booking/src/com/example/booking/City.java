package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final String name;
    private final List<Theater> theaters = new ArrayList<>();

    public City(String name) { this.name = name; }
    public String getName() { return name; }
    public void addTheater(Theater theater) { theaters.add(theater); }
    public List<Theater> getTheaters() { return theaters; }
}
