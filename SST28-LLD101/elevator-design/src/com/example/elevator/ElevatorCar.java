package com.example.elevator;

import java.util.TreeSet;

public class ElevatorCar {
    private final String id;
    private int currentFloor;
    private Direction direction;
    private Status state;
    private final int capacity;
    private double currentLoad;

    private final TreeSet<Integer> upStops = new TreeSet<>();
    private final TreeSet<Integer> downStops = new TreeSet<>();

    private final Door door;
    private final Display display;
    private final Button[] internalButtons;

    public ElevatorCar(String id, int numFloors, int capacity) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = Status.STOPPED;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.door = new Door();
        this.display = new Display();
        this.internalButtons = new Button[numFloors];
        for (int i = 0; i < numFloors; i++) {
            internalButtons[i] = new Button("Floor " + i);
        }
    }

    public synchronized void addInternalRequest(int floor) {
        if (state == Status.UNDER_MAINTENANCE) return;
        internalButtons[floor].press();
        if (floor > currentFloor) {
            upStops.add(floor);
        } else if (floor < currentFloor) {
            downStops.add(floor);
        } else {
            openDoor();
        }
        updateDirection();
    }

    public synchronized void addExternalRequest(int floor, Direction dir) {
        if (state == Status.UNDER_MAINTENANCE) return;
        if (dir == Direction.UP) {
            upStops.add(floor);
        } else {
            downStops.add(floor);
        }
        updateDirection();
    }

    private void updateDirection() {
        if (direction == Direction.IDLE) {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
                state = Status.MOVING;
            } else if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
                state = Status.MOVING;
            }
        }
    }

    public void step() {
        if (state == Status.UNDER_MAINTENANCE) return;

        if (state == Status.MOVING) {
            if (direction == Direction.UP) {
                currentFloor++;
                System.out.println("Elevator " + id + " moving UP to floor " + currentFloor);
                if (upStops.contains(currentFloor)) {
                    stopAt(currentFloor);
                }
            } else if (direction == Direction.DOWN) {
                currentFloor--;
                System.out.println("Elevator " + id + " moving DOWN to floor " + currentFloor);
                if (downStops.contains(currentFloor)) {
                    stopAt(currentFloor);
                }
            }

            if (upStops.isEmpty() && downStops.isEmpty()) {
                direction = Direction.IDLE;
                state = Status.STOPPED;
            } else if (direction == Direction.UP && upStops.tailSet(currentFloor, false).isEmpty()) {
                if (!downStops.isEmpty()) {
                    direction = Direction.DOWN;
                } else {
                    direction = Direction.IDLE;
                    state = Status.STOPPED;
                }
            } else if (direction == Direction.DOWN && downStops.headSet(currentFloor, false).isEmpty()) {
                if (!upStops.isEmpty()) {
                    direction = Direction.UP;
                } else {
                    direction = Direction.IDLE;
                    state = Status.STOPPED;
                }
            }
        }
        display.update(currentFloor, direction);
    }

    private void stopAt(int floor) {
        state = Status.STOPPED;
        openDoor();
        upStops.remove(floor);
        downStops.remove(floor);
        internalButtons[floor].reset();
        closeDoor();
        if (!upStops.isEmpty() || !downStops.isEmpty()) {
            state = Status.MOVING;
        }
    }

    public void openDoor() {
        if (currentLoad > 750) {
            System.out.println("ALARM! Weight limit exceeded (" + currentLoad + "kg). Door won't close.");
            return;
        }
        door.open();
    }

    public void closeDoor() {
        if (currentLoad > 750) {
            System.out.println("ALARM! Weight limit exceeded. Cannot close door.");
            return;
        }
        door.close();
    }

    public boolean isIdle() {
        return state == Status.STOPPED && upStops.isEmpty() && downStops.isEmpty();
    }

    // Getters and Setters
    public String getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public Status getState() { return state; }
    public void setState(Status state) { this.state = state; }
    public double getCurrentLoad() { return currentLoad; }
    public void setCurrentLoad(double currentLoad) { this.currentLoad = currentLoad; }
}
