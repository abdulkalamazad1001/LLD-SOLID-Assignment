package com.example.pen;

public class Refill {
    private final Ink ink;
    private final Nib nib;
    private double currentLevel;
    private final double maxCapacity;

    public Refill(Ink ink, Nib nib, double maxCapacity) {
        this.ink = ink;
        this.nib = nib;
        this.maxCapacity = maxCapacity;
        this.currentLevel = maxCapacity;
    }

    public boolean consumeInk(double amount) {
        if (currentLevel >= amount) {
            currentLevel -= amount;
            return true;
        }
        return false;
    }

    public double getInkLevel() {
        return currentLevel;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public Ink getInk() {
        return ink;
    }

    public Nib getNib() {
        return nib;
    }
}
