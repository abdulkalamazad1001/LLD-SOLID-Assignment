package com.example.elevator;

public class ElevatorEvent {
    private final String type;
    private final String description;

    public ElevatorEvent(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() { return type; }
    public String getDescription() { return description; }
}
