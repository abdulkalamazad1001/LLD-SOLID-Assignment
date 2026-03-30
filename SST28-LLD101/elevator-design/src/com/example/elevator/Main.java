package com.example.elevator;

public class Main {
    public static void main(String[] args) {
        int numFloors = 10;
        int numElevators = 2;
        Elevator controller = Elevator.getInstance(numElevators, numFloors);
        Sensor sensor = new Sensor();

        controller.getElevators().forEach(sensor::addElevator);

        System.out.println("--- Starting Elevator Simulation ---");

        System.out.println("\n[External Request] Floor 3, Direction: UP");
        controller.submitExternalRequest(3, Direction.UP);

        System.out.println("\n--- Step 1 ---");
        controller.step();
        System.out.println("--- Step 2 ---");
        controller.step();
        System.out.println("--- Step 3 ---");
        controller.step();

        System.out.println("\n[Action] Someone heavy (800kg) enters Elevator 1 at floor 3");
        sensor.updateLoad("E1", 800);
        controller.submitInternalRequest("E1", 7);

        System.out.println("\n--- Step 4 (Expect weight alarm) ---");
        controller.step();

        System.out.println("\n[Action] Someone (100kg) leaves Elevator 1 at floor 3");
        sensor.updateLoad("E1", 700);

        System.out.println("\n--- Step 5 (Expect door to close and elevator to move) ---");
        controller.step();

        System.out.println("\n[Action] Switching to Shortest Seek Time First (SSTF) strategy");
        controller.setAssignmentStrategy(new LookControllerStrategy());

        System.out.println("\n[External Request] Floor 5, Direction: DOWN");
        controller.submitExternalRequest(5, Direction.DOWN);

        System.out.println("\n--- Step 6 ---");
        controller.step();
        System.out.println("--- Step 7 ---");
        controller.step();

        System.out.println("\n--- Simulation Complete ---");
    }
}
