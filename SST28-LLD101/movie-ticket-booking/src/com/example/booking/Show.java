package com.example.booking;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Theater theater;
    private final LocalDateTime startTime;
    private final Map<String, SeatStatus> seatAvailability = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Show(String id, Movie movie, Theater theater, Screen screen, LocalDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        for (Seat seat : screen.getSeats()) {
            seatAvailability.put(seat.getId(), SeatStatus.AVAILABLE);
        }
    }

    public String getId() { return id; }
    public Movie getMovie() { return movie; }
    public Theater getTheater() { return theater; }
    public Screen getScreen() { return screen; }
    public LocalDateTime getStartTime() { return startTime; }
    public Map<String, SeatStatus> getSeatAvailability() { return seatAvailability; }

    public boolean bookSeats(List<String> seatIds) {
        lock.lock();
        try {
            for (String seatId : seatIds) {
                if (seatAvailability.get(seatId) != SeatStatus.AVAILABLE) {
                    return false;
                }
            }
            for (String seatId : seatIds) {
                seatAvailability.put(seatId, SeatStatus.BOOKED);
            }
            return true;
        } finally {
            lock.unlock();
        }
    }
}
