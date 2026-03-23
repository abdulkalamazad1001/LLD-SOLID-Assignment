# Multilevel Parking Lot Design

A Java-based Low-Level Design (LLD) for a robust multilevel parking lot system.

## Requirements
- Support three types of parking slots: **Small** (2-wheelers), **Medium** (cars), **Large** (buses).
- Maintain different hourly parking charges for each slot type.
- **On Entry:** assign a parking slot, generate a parking ticket storing vehicle details, allocated slot, type, and entry time.
- **On Exit:** calculate the parking duration, generate the bill, and return the total amount to be paid.
- The parking lot has **multiple entry gates**.
- Always assign the **nearest available compatible slot** based on the vehicle’s entry gate.
- Smaller vehicles can park in larger slots (2-wheeler in S/M/L, car in M/L, bus in L).
- Billing should be based on the **allocated slot type**, not the vehicle type.
- APIs to be Supported:
  - `park(vehicleDetails, entryTime, requestedSlotType, entryGateID)`
  - `status()`
  - `exit(parkingTicket, exitTime)`

## Design Explanation & Approach

This system is built using Object-Oriented principles and design patterns (Facade, Strategy) to satisfy variable slot allocations, distance-based selection, and accurate pricing.

1. **Facade Pattern (`ParkingLotSystem`)**
   Provides a clean unified interface (`park`, `status`, `exit`) for the complex subsystem of slots, tickets, and strategies. Clients don't manually assign slots or calculate times.

2. **Strategy Pattern (`PricingStrategy` & `SlotAssignmentStrategy`)**
   - Pricing is abstracted behind a strategy. If we introduce weekend rates, flat rates, or dynamic surge pricing, we only write a new Strategy without touching the core system.
   - Slot assignment is abstracted. Currently, it finds the **nearest compatible slot** from the requesting gate.

3. **Multi-gate Distance Logic (`distanceToGates` Map inside `Slot`)**
   To support multiple levels and gates accurately, each `Slot` is aware of its direct distance (or cost) to any given gate. When `Gate B` requests a spot, the strategy instantly scores all slots relative to Gate B and picks the absolute closest one available. 

4. **Slot Type Flexibility**
   A smaller vehicle can park in a larger slot (`TWO_WHEELER` -> `SMALL`, `MEDIUM`, `LARGE`).
   However, calculating the exit bill uses `ticket.getAllocatedSlot().getType()`, satisfying the requirement: *Billing should be based on the allocated slot type, not the vehicle type.*

## Build & Run

Ensure you have a JDK installed (Java 8+). 

```bash
# Navigate to the source folder
cd multilevel-parking-lot/src

# Compile all classes
javac com/example/parking/enums/*.java com/example/parking/models/*.java com/example/parking/services/*.java com/example/parking/App.java

# Run the simulation App
java com.example.parking.App
```
