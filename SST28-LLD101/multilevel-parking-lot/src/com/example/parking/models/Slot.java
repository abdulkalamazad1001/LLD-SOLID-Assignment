package com.example.parking.models;

import com.example.parking.enums.SlotType;

import java.util.Collections;
import java.util.Map;

public class Slot {

    private final String id;
    private final SlotType type;
    private final Map<String, Integer> distanceToGates; // GateID -> Distance

    private boolean isOccupied;
    private Vehicle currentVehicle;

    public Slot(String id, SlotType type, Map<String, Integer> distanceToGates) {
        this.id = id;
        this.type = type;
        this.distanceToGates = distanceToGates;
        this.isOccupied = false;
    }

    public String getId() {
        return id;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public Map<String, Integer> getDistanceToGates() {
        return Collections.unmodifiableMap(distanceToGates);
    }

    public void park(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.isOccupied = false;
    }
}
