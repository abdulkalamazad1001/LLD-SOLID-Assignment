package com.example.parking.models;

import java.util.UUID;

public class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final Slot allocatedSlot;
    private final long entryTime;
    private final String entryGateId;

    public Ticket(Vehicle vehicle, Slot allocatedSlot, long entryTime, String entryGateId) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.allocatedSlot = allocatedSlot;
        this.entryTime = entryTime;
        this.entryGateId = entryGateId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Slot getAllocatedSlot() {
        return allocatedSlot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public String getEntryGateId() {
        return entryGateId;
    }
}
