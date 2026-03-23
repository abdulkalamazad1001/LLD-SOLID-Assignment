package com.example.parking.services;

import com.example.parking.enums.SlotType;

public class PricingStrategy {

    private final double smallRate;
    private final double mediumRate;
    private final double largeRate;

    public PricingStrategy(double smallRate, double mediumRate, double largeRate) {
        this.smallRate = smallRate;
        this.mediumRate = mediumRate;
        this.largeRate = largeRate;
    }

    public double calculateBill(SlotType slotType, long entryTime, long exitTime) {
        long durationMillis = exitTime - entryTime;
        long durationHours = (long) Math.ceil((double) durationMillis / (1000 * 60 * 60));

        // Minimum 1 hour charge
        if (durationHours == 0) {
            durationHours = 1;
        }

        switch (slotType) {
            case SMALL:
                return durationHours * smallRate;
            case MEDIUM:
                return durationHours * mediumRate;
            case LARGE:
                return durationHours * largeRate;
            default:
                throw new IllegalArgumentException("Unknown slot type: " + slotType);
        }
    }
}
