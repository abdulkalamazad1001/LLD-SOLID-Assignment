package com.example.booking;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class BookingManager implements Serializable, Cloneable {
    private static volatile BookingManager instance;
    private final Map<String, City> cities = new HashMap<>();
    private final Map<String, Movie> movies = new HashMap<>();
    private final List<Show> shows = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    // Reflection protection
    private BookingManager() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() to create the Manager.");
        }
    }

    public static BookingManager getInstance() {
        if (instance == null) {
            synchronized (BookingManager.class) {
                if (instance == null) {
                    instance = new BookingManager();
                }
            }
        }
        return instance;
    }

    // Admin APIs
    public void addCity(City city) { cities.put(city.getName(), city); }
    public void addMovie(Movie movie) { movies.put(movie.getId(), movie); }
    public void addTheater(String cityName, Theater theater) {
        City city = cities.get(cityName);
        if (city != null) city.addTheater(theater);
    }
    public void addShow(Show show) { shows.add(show); }

    // Customer Search APIs
    public List<Movie> getMoviesByCity(String cityName) {
        City city = cities.get(cityName);
        if (city == null) return new ArrayList<>();
        return shows.stream()
                .filter(s -> city.getTheaters().contains(s.getTheater()))
                .map(Show::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Theater> getTheatersByCity(String cityName) {
        City city = cities.get(cityName);
        return (city != null) ? city.getTheaters() : new ArrayList<>();
    }

    public List<Show> getShows(String cityName, String movieTitle) {
        return shows.stream()
                .filter(s -> s.getMovie().getTitle().equalsIgnoreCase(movieTitle))
                .collect(Collectors.toList());
    }

    // Booking logic (Thread-safe)
    public synchronized Booking createBooking(String userId, String showId, List<String> seatIds) {
        Show show = shows.stream().filter(s -> s.getId().equals(showId)).findFirst().orElse(null);
        if (show == null) return null;

        if (show.bookSeats(seatIds)) {
            List<Seat> selectedSeats = show.getScreen().getSeats().stream()
                    .filter(s -> seatIds.contains(s.getId()))
                    .collect(Collectors.toList());
            Booking booking = new Booking("B" + (bookings.size() + 1), show, selectedSeats);
            bookings.add(booking);
            return booking;
        }
        return null;
    }

    public List<Booking> getBookings() { return new ArrayList<>(bookings); }

    // Serialization protection
    protected Object readResolve() { return getInstance(); }

    // Clone protection
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }
}
