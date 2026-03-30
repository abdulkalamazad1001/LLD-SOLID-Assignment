package com.example.elevator;

public class Display {
    private int floor;
    private Direction direction;

    public void update(int floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public void show() {
        System.out.println("Display: [ Floor: " + floor + " | Direction: " + direction + " ]");
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }
}
