package com.example.booking;

public class Seat {
    private final String id;
    private final int row;
    private final int column;
    private final SeatType type;
    private final double price;

    public Seat(String id, int row, int col, SeatType type, double price) {
        this.id = id;
        this.row = row;
        this.column = col;
        this.type = type;
        this.price = price;
    }

    public String getId() { return id; }
    public SeatType getType() { return type; }
    public double getPrice() { return price; }
}
