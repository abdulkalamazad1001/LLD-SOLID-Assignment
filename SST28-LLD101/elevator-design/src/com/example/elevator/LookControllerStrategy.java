package com.example.elevator;

import java.util.List;

public class LookControllerStrategy implements ControllerStrategy {
    @Override
    public ElevatorCar assign(List<ElevatorCar> elevators, ElevatorRequest request) {
        ElevatorCar bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }
        return bestElevator;
    }
}
