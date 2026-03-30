package com.example.booking;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BookingManager manager = BookingManager.getInstance();

        // 1. Setup Sample Data
        City city = new City("Mumbai");
        Theater theater = new Theater("T1", "PVR Cinemas", "Mumbai Central");
        Screen screen1 = new Screen("S1");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 10; j++) {
                String seatId = "R" + i + "C" + j;
                SeatType type = i < 3 ? SeatType.PLATINUM : (i < 5 ? SeatType.GOLD : SeatType.SILVER);
                double price = type == SeatType.PLATINUM ? 500 : (type == SeatType.GOLD ? 350 : 200);
                screen1.addSeat(new Seat(seatId, i, j, type, price));
            }
        }
        theater.addScreen(screen1);
        city.addTheater(theater);
        manager.addCity(city);

        Movie movie = new Movie("M1", "Inception", 148, "Sci-Fi");
        manager.addMovie(movie);

        Show show = new Show("SH1", movie, theater, screen1, LocalDateTime.now().plusHours(3));
        manager.addCity(city);
        manager.addMovie(movie);
        manager.addShow(show);

        System.out.println("--- Starting Movie Ticket Booking System Simulation ---");
        System.out.println("City: " + city.getName());
        System.out.println("Theater: " + theater.getName());
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Show ID: " + show.getId());

        // 2. Multi-threaded Booking Attempt (Concurrency Test)
        // Two users try to book the EXACT SAME seats (R1C1 and R1C2) at the same time
        List<String> targetSeats = Arrays.asList("R1C1", "R1C2");
        System.out.println("\n[Concurrency Test] Two users attempting to book seats " + targetSeats + " simultaneously...");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable user1 = () -> {
            Booking b = manager.createBooking("U1", "SH1", targetSeats);
            if (b != null) {
                System.out.println("User 1: Successfully booked seats " + targetSeats + ". Total: ₹" + b.getTotalAmount());
            } else {
                System.out.println("User 1: Failed to book (Seats might be taken).");
            }
        };

        Runnable user2 = () -> {
            Booking b = manager.createBooking("U2", "SH1", targetSeats);
            if (b != null) {
                System.out.println("User 2: Successfully booked seats " + targetSeats + ". Total: ₹" + b.getTotalAmount());
            } else {
                System.out.println("User 2: Failed to book (Seats might be taken).");
            }
        };

        executor.execute(user1);
        executor.execute(user2);

        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. Different Seat Booking Attempt
        System.out.println("\n[Booking Test] Another user attempting to book seats R2C1 and R2C2...");
        List<String> otherSeats = Arrays.asList("R2C1", "R2C2");
        Booking b3 = manager.createBooking("U3", "SH1", otherSeats);
        if (b3 != null) {
            System.out.println("User 3: Successfully booked seats " + otherSeats + ". Total: ₹" + b3.getTotalAmount());
        }

        System.out.println("\n--- Final Booking Status ---");
        System.out.println("Total Bookings in System: " + manager.getBookings().size());
        for (Booking b : manager.getBookings()) {
            System.out.println("Booking ID: " + b.getBookingId() + " | Amount: ₹" + b.getTotalAmount() + " | Status: " + b.getStatus());
        }

        System.out.println("\n--- Simulation Complete ---");
    }
}
