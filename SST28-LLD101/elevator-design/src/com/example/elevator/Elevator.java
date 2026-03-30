package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private static volatile Elevator instance;
    private final List<ElevatorCar> elevators;
    private ControllerStrategy assignmentStrategy;

    private Elevator(int numElevators, int numFloors) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new ElevatorCar("E" + (i + 1), numFloors, 750));
        }
        this.assignmentStrategy = new FCFSStrategy(); // Default strategy
    }

    public static Elevator getInstance(int numElevators, int numFloors) {
        if (instance == null) {
            synchronized (Elevator.class) {
                if (instance == null) {
                    instance = new Elevator(numElevators, numFloors);
                }
            }
        }
        return instance;
    }

    public void setAssignmentStrategy(ControllerStrategy strategy) {
        this.assignmentStrategy = strategy;
    }

    public void submitExternalRequest(int floor, Direction direction) {
        ElevatorRequest request = new ElevatorRequest(floor, -1, direction, false);
        ElevatorCar elevatorCar = assignmentStrategy.assign(elevators, request);
        System.out.println("Assigning Elevator " + elevatorCar.getId() + " to external request at floor " + floor);
        elevatorCar.addExternalRequest(floor, direction);
    }

    public void submitInternalRequest(String elevatorId, int destinationFloor) {
        for (ElevatorCar elevatorCar : elevators) {
            if (elevatorCar.getId().equals(elevatorId)) {
                elevatorCar.addInternalRequest(destinationFloor);
                return;
            }
        }
        System.out.println("Elevator " + elevatorId + " not found.");
    }

    public void step() {
        for (ElevatorCar elevatorCar : elevators) {
            elevatorCar.step();
        }
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }
}
