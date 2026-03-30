package com.example.elevator;

public class ElevatorRequest {
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;
    private final boolean isInternal;

    public ElevatorRequest(int sourceFloor, int destinationFloor, Direction direction, boolean isInternal) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.isInternal = isInternal;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isInternal() {
        return isInternal;
    }
}
