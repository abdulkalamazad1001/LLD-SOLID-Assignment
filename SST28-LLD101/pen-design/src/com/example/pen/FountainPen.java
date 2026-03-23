package com.example.pen;

public class FountainPen extends Pen {
    private final Ink ink;
    private final Nib nib;
    private double currentLevel;
    private final double maxCapacity;

    public FountainPen(String brand, Ink ink, Nib nib, double maxCapacity) {
        super(brand);
        this.ink = ink;
        this.nib = nib;
        this.maxCapacity = maxCapacity;
        this.currentLevel = maxCapacity;
    }

    public void refillInk(double amount) {
        if (isOpen()) {
            System.out.println("Please close the " + getBrand() + " fountain pen before refilling.");
            return;
        }
        if (currentLevel + amount <= maxCapacity) {
            currentLevel += amount;
            System.out.printf("Refilled fountain pen. Current ink level: %.2f / %.2f\n", currentLevel, maxCapacity);
        } else {
            System.out.println("Overflow warning! Cannot add " + amount + " units. Max capacity is " + maxCapacity);
            currentLevel = maxCapacity; // Fill to the brim
            System.out.println("Filled to max capacity.");
        }
    }

    @Override
    public void write(String content) {
        if (!isOpen()) {
            System.out.println("Cannot write! The " + getBrand() + " fountain pen is closed.");
            return;
        }

        // Fountain pens might consume slightly more ink per character
        double inkNeeded = content.length() * 0.15;

        if (currentLevel >= inkNeeded) {
            currentLevel -= inkNeeded;
            System.out.println("[" + ink.getColor() + " FOUNTAIN] Calligraphy Writing: " + content);
            System.out.printf("Ink remaining: %.2f / %.2f\n", currentLevel, maxCapacity);
        } else {
            System.out.println("Not enough ink in the fountain pen! Please refill ink directly.");
        }
    }
}
