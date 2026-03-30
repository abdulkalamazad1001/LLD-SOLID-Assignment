package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private final List<ElevatorCar> elevators = new ArrayList<>();

    public void addElevator(ElevatorCar elevator) {
        elevators.add(elevator);
    }

    public void updateLoad(String elevatorId, double load) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId().equals(elevatorId)) {
                elevator.setCurrentLoad(load);
                System.out.println("Sensor: Elevator " + elevatorId + " load updated to " + load + "kg.");
                return;
            }
        }
    }
}
