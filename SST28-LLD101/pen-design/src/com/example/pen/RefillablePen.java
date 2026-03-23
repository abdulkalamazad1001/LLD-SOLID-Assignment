package com.example.pen;

public class RefillablePen extends Pen {
    private Refill refill;

    public RefillablePen(String brand, Refill refill) {
        super(brand);
        this.refill = refill;
    }

    public void changeRefill(Refill newRefill) {
        if (isOpen()) {
            System.out.println("Cannot change refill while " + getBrand() + " pen is open!");
            return;
        }
        this.refill = newRefill;
        System.out.println("Refill changed successfully for " + getBrand() + " pen.");
    }

    @Override
    public void write(String content) {
        if (!isOpen()) {
            System.out.println("Cannot write! The " + getBrand() + " pen is closed.");
            return;
        }
        if (refill == null) {
            System.out.println("No refill found inside the pen.");
            return;
        }

        // Simulating ink consumption: 0.1 units per character
        double inkNeeded = content.length() * 0.1;

        if (refill.consumeInk(inkNeeded)) {
            System.out.println(
                    "[" + refill.getInk().getColor() + " " + refill.getInk().getType() + "] Writing: " + content);
            System.out.printf("Ink remaining: %.2f / %.2f\n", refill.getInkLevel(), refill.getMaxCapacity());
        } else {
            System.out.println("Not enough ink to write! Please change the refill.");
        }
    }
}
