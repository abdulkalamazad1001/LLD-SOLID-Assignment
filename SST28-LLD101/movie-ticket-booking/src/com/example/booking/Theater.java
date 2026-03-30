package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private final String id;
    private final String name;
    private final String address;
    private final List<Screen> screens = new ArrayList<>();

    public Theater(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void addScreen(Screen screen) { screens.add(screen); }
    public List<Screen> getScreens() { return screens; }
}
