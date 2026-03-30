# Movie Ticket Booking System - LLD Design

A robust, thread-safe Low-Level Design (LLD) for a Movie Ticket Booking System like BookMyShow. This project demonstrates **SOLID principles**, the **Singleton pattern**, and **Concurrency Management** in a multi-threaded environment.

## 📋 Problem Statement
Design a system that allows customers to search for movies and theaters in a city, view available shows/seats, and book tickets. Simultaneously, administrators can manage movies, theaters, and schedule shows. The system must handle concurrent booking attempts for the same seats efficiently.

## 🛠 Features
- **Singleton Pattern**: The `BookingManager` uses a thread-safe, double-checked locking mechanism to ensure a single global state.
- **Seat Tiering**: Support for various seat categories (Silver, Gold, Platinum) with dynamic pricing.
- **Concurrency Control**: Utilizes `ReentrantLock` at the `Show` level to prevent double-booking, ensuring that seat availability is correctly maintained across multiple concurrent sessions.
- **API Interfaces**: Cleanly separated Customer and Admin functionalities.

## 🏗 System Design (UML)

```mermaid
classDiagram
    class BookingManager {
        -volatile BookingManager instance
        -Map cities
        -Map movies
        -List shows
        +getInstance() BookingManager
        +getMoviesByCity(String city)
        +createBooking(String userId, String showId, List seatIds)
    }

    class City {
        -String name
        -List theaters
    }

    class Theater {
        -String id
        -String name
        -List screens
    }

    class Screen {
        -String id
        -List seats
    }

    class Movie {
        -String id
        -String title
        -int duration
    }

    class Show {
        -String id
        -Movie movie
        -Screen screen
        -LocalDateTime startTime
        -Map seatAvailability
        -ReentrantLock lock
        +bookSeats(List seatIds)
    }

    class Booking {
        -String bookingId
        -Show show
        -List seats
        -double totalAmount
        -BookingStatus status
    }

    class Seat {
        -String id
        -SeatType type
        -double price
    }

    BookingManager "1" *-- "many" City 
    BookingManager "1" *-- "many" Movie
    BookingManager "1" *-- "many" Show
    City "1" *-- "many" Theater
    Theater "1" *-- "many" Screen
    Screen "1" *-- "many" Seat
    Show o-- Movie
    Show o-- Screen
    Booking o-- Show
    Booking o-- Seat
```

## 🚀 How to Run the Simulation

1. **Compile the code**:
   ```powershell
   javac SST28-LLD101/movie-ticket-booking/src/com/example/booking/*.java
   ```

2. **Run the simulation**:
   ```powershell
   java -cp SST28-LLD101/movie-ticket-booking/src com.example.booking.Main
   ```

## 🧪 Simulation Output Highlights
- **Concurrency Test**: The simulation spawns two threads that attempt to book the **exact same seats** at the same millisecond. The system ensures only one user succeeds, while the other receives a "Seats might be taken" failure message.
- **Booking Summary**: At the end, a summary of all successful bookings, including seat types and total revenue, is displayed.

## 🎓 Key Learnings
- **SOLID Consistency**: The project is structured with single responsibilities (e.g., `Show` handles its own seat logic).
- **Thread Safety**: Demonstrated how to use synchronized patterns and explicit locks to manage shared resources in a high-concurrency booking environment.
