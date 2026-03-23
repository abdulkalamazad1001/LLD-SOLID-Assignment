package com.example.pen;

public class Ink {
    private final Color color;
    private final InkType type;

    public Ink(Color color, InkType type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public InkType getType() {
        return type;
    }
}
