package com.example.booking;

import java.util.List;

public class Booking {
    private final String bookingId;
    private final Show show;
    private final List<Seat> seats;
    private final double totalAmount;
    private BookingStatus status;

    public Booking(String id, Show show, List<Seat> seats) {
        this.bookingId = id;
        this.show = show;
        this.seats = seats;
        this.totalAmount = seats.stream().mapToDouble(Seat::getPrice).sum();
        this.status = BookingStatus.CONFIRMED;
    }

    public String getBookingId() { return bookingId; }
    public Show getShow() { return show; }
    public List<Seat> getSeats() { return seats; }
    public double getTotalAmount() { return totalAmount; }
    public BookingStatus getStatus() { return status; }
}
