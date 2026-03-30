package com.example.parking.services;

import com.example.parking.enums.SlotType;
import com.example.parking.enums.VehicleType;
import com.example.parking.models.Slot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SlotAssignmentStrategy {

    public Slot findNearestCompatibleSlot(VehicleType vType, SlotType requestedType, String gateId,
            List<Slot> allSlots) {
        if (!isCompatible(vType, requestedType)) {
            throw new IllegalArgumentException(
                    "Vehicle type " + vType + " cannot park in requested slot type " + requestedType);
        }

        List<Slot> availableSlots = allSlots.stream()
                .filter(s -> !s.isOccupied())
                .filter(s -> s.getType() == requestedType || isUpgradedCompatible(vType, s.getType()))
                .sorted(Comparator.comparingInt(s -> s.getDistanceToGates().getOrDefault(gateId, Integer.MAX_VALUE)))
                .collect(Collectors.toList());

        if (availableSlots.isEmpty()) {
            return null;
        }

        return availableSlots.stream()
                .filter(s -> s.getType() == requestedType)
                .findFirst()
                .orElse(availableSlots.get(0));
    }

    private boolean isCompatible(VehicleType vType, SlotType sType) {
        switch (vType) {
            case TWO_WHEELER:
                return true; // Can park in small, medium, large
            case CAR:
                return sType == SlotType.MEDIUM || sType == SlotType.LARGE;
            case BUS:
                return sType == SlotType.LARGE;
            default:
                return false;
        }
    }

    private boolean isUpgradedCompatible(VehicleType vType, SlotType sType) {
        return isCompatible(vType, sType);
    }
}
