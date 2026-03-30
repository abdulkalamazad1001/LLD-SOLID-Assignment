package com.example.parking;

import com.example.parking.enums.SlotType;
import com.example.parking.enums.VehicleType;
import com.example.parking.models.Slot;
import com.example.parking.models.Ticket;
import com.example.parking.models.Vehicle;
import com.example.parking.services.ParkingLotSystem;
import com.example.parking.services.PricingStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Slot s1 = new Slot("S1", SlotType.SMALL, createDistances(10, 50));
        Slot s2 = new Slot("S2", SlotType.SMALL, createDistances(20, 40));
        Slot s3 = new Slot("S3", SlotType.MEDIUM, createDistances(30, 30));
        Slot s4 = new Slot("S4", SlotType.LARGE, createDistances(40, 20));

        PricingStrategy pricingStrategy = new PricingStrategy(10.0, 20.0, 30.0);
        ParkingLotSystem system = new ParkingLotSystem(Arrays.asList(s1, s2, s3, s4), pricingStrategy);

        System.out.println("Initial status: " + system.status());

        // Park a bike at Gate A
        Vehicle bike = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        long timeNow = System.currentTimeMillis();
        Ticket t1 = system.park(bike, timeNow, SlotType.SMALL, "Gate A");
        System.out.println(
                "Parked bike in: " + t1.getAllocatedSlot().getId() + " (" + t1.getAllocatedSlot().getType() + ")");

        // Park a car at Gate B
        Vehicle car = new Vehicle("DL-4C-9999", VehicleType.CAR);
        Ticket t2 = system.park(car, timeNow, SlotType.MEDIUM, "Gate B");
        System.out.println(
                "Parked car in: " + t2.getAllocatedSlot().getId() + " (" + t2.getAllocatedSlot().getType() + ")");

        Vehicle bike2 = new Vehicle("MH-12-8888", VehicleType.TWO_WHEELER);
        Ticket t3 = system.park(bike2, timeNow, SlotType.SMALL, "Gate A");
        System.out.println(
                "Parked bike2 in: " + t3.getAllocatedSlot().getId() + " (" + t3.getAllocatedSlot().getType() + ")");

        System.out.println("Status after parking: " + system.status());

        // Exit bike after 3 hours
        long exitTime = timeNow + (3 * 60 * 60 * 1000);
        double bill = system.exit(t1, exitTime);
        System.out.println("Bike exited. Bill for " + t1.getAllocatedSlot().getType() + ": $" + bill);

        System.out.println("Final status: " + system.status());
    }

    private static Map<String, Integer> createDistances(int gateA, int gateB) {
        Map<String, Integer> dist = new HashMap<>();
        dist.put("Gate A", gateA);
        dist.put("Gate B", gateB);
        return dist;
    }
}
