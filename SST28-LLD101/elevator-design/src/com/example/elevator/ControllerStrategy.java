package com.example.elevator;

import java.util.List;

public interface ControllerStrategy {
    ElevatorCar assign(List<ElevatorCar> elevators, ElevatorRequest request);
}
