package com.example.parking.services;

import com.example.parking.enums.SlotType;

import com.example.parking.models.Slot;
import com.example.parking.models.Ticket;
import com.example.parking.models.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {

    private final List<Slot> slots;
    private final Map<String, Ticket> activeTickets;
    private final SlotAssignmentStrategy assignmentStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingLotSystem(List<Slot> slots, PricingStrategy pricingStrategy) {
        this.slots = slots;
        this.activeTickets = new HashMap<>();
        this.assignmentStrategy = new SlotAssignmentStrategy();
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket park(Vehicle vehicle, long entryTime, SlotType requestedSlotType, String entryGateId) {
        Slot allocatedSlot = assignmentStrategy.findNearestCompatibleSlot(vehicle.getType(), requestedSlotType,
                entryGateId, slots);

        if (allocatedSlot == null) {
            throw new IllegalStateException("No available compatible slots found.");
        }

        allocatedSlot.park(vehicle);
        Ticket ticket = new Ticket(vehicle, allocatedSlot, entryTime, entryGateId);
        activeTickets.put(ticket.getTicketId(), ticket);

        return ticket;
    }

    public Map<SlotType, Integer> status() {
        Map<SlotType, Integer> availability = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availability.put(type, 0);
        }

        for (Slot slot : slots) {
            if (!slot.isOccupied()) {
                availability.put(slot.getType(), availability.get(slot.getType()) + 1);
            }
        }
        return availability;
    }

    public double exit(Ticket parkingTicket, long exitTime) {
        if (!activeTickets.containsKey(parkingTicket.getTicketId())) {
            throw new IllegalArgumentException("Invalid ticket or vehicle already exited.");
        }

        Slot slot = parkingTicket.getAllocatedSlot();
        double bill = pricingStrategy.calculateBill(slot.getType(), parkingTicket.getEntryTime(), exitTime);

        slot.removeVehicle();
        activeTickets.remove(parkingTicket.getTicketId());

        return bill;
    }
}
