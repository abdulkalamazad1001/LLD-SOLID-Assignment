package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String id;
    private final List<Seat> seats = new ArrayList<>();

    public Screen(String id) { this.id = id; }
    public String getId() { return id; }
    public void addSeat(Seat seat) { seats.add(seat); }
    public List<Seat> getSeats() { return seats; }
}
