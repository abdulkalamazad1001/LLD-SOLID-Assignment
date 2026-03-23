package com.example.pen;

public abstract class Pen {
    private boolean isOpen;
    private final String brand;

    public Pen(String brand) {
        this.brand = brand;
        this.isOpen = false;
    }

    public void start() {
        isOpen = true;
        System.out.println(brand + " pen is now open.");
    }

    public void close() {
        isOpen = false;
        System.out.println(brand + " pen is now closed.");
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getBrand() {
        return brand;
    }

    // Abstract method to be implemented by child classes
    public abstract void write(String content);
}
