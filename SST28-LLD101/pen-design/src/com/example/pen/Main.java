package com.example.pen;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Refillable Pen (Gel) ===");
        Ink gelInk = new Ink(Color.BLUE, InkType.GEL);
        Nib gelNib = new Nib(0.5);
        Refill blueGelRefill = new Refill(gelInk, gelNib, 5.0); // 5 units of ink

        RefillablePen reynolds = new RefillablePen("Reynolds Trimax", blueGelRefill);

        reynolds.write("Hello"); // Should fail, closed
        reynolds.start();
        reynolds.write("This is a test of a refillable gel pen.");
        reynolds.write("Writing more clears the ink.");

        // Attempt to change refill while open
        Ink redInk = new Ink(Color.RED, InkType.BALLPOINT);
        Refill redRefill = new Refill(redInk, new Nib(0.7), 10.0);
        reynolds.changeRefill(redRefill); // Should warn

        reynolds.close();
        reynolds.changeRefill(redRefill); // Success

        reynolds.start();
        reynolds.write("Now writing in red!");
        reynolds.close();

        System.out.println("\n=== Testing Fountain Pen ===");
        Ink darkInk = new Ink(Color.BLACK, InkType.FOUNTAIN);
        Nib calligraphyNib = new Nib(1.2);
        FountainPen parker = new FountainPen("Parker", darkInk, calligraphyNib, 8.0);

        parker.start();
        parker.write("A beautifully crafted sentence."); // Consumes 0.15 per char
        parker.write("And another one to drain more ink.");
        parker.close();

        parker.refillInk(5.0); // Refill while closed
    }
}
