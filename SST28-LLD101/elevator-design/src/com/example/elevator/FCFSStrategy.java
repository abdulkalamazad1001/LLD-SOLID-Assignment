package com.example.elevator;

import java.util.List;

public class FCFSStrategy implements ControllerStrategy {
    @Override
    public ElevatorCar assign(List<ElevatorCar> elevators, ElevatorRequest request) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.isIdle()) {
                return elevator;
            }
        }
        return elevators.get(0); // Default to first elevator
    }
}
