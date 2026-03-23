# Pen Design LLD

A Java-based Low-Level Design (LLD) for a Pen system, utilizing core Object-Oriented Programming (OOP) principles.

## Requirements

1. **Entities Needed:** Provide a software architecture that represents a `Pen`, `Ink`, `Nib`, and `Refill`.
2. **Behavior (Pen):** A pen can open (`start`), close (`close`), and `write`. Writing consumes ink. A closed pen cannot write.
3. **Specific Pens:**
   - **Refillable Pen:** Uses a distinct `Refill` object (which holds ink and nib). The refill can be replaced.
   - **Fountain Pen:** Does not use a refill. Holds its `Ink` and `Nib` directly. Can be refilled manually using liquid ink.
4. **Enums:**
   - `Color` (BLACK, BLUE, RED)
   - `InkType` (GEL, BALLPOINT, FOUNTAIN)

## Design Discussion

The design translates physical objects into software using:
- **Abstraction & Inheritance:** `Pen` is an abstract base class. The `write()` method is abstract because a `RefillablePen` writes using ink from its `Refill`, whereas a `FountainPen` uses its own internal ink reservoir. 
- **Composition vs. Aggregation:**
   - A `Refill` *composes* an `Ink` and a `Nib`.
   - A `FountainPen` *composes* its own `Ink` and `Nib` directly.
   - A `RefillablePen` *aggregates* a `Refill` (because refills can be swapped dynamically via `changeRefill()`).

## Build & Run

```bash
# Navigate to source
cd pen-design/src

# Compile everything
javac com/example/pen/*.java

# Run tests via Main
java com.example.pen.Main
```
