package com.example.elevator;

public class Button {
    private boolean isPressed;
    private final String label;

    public Button(String label) {
        this.label = label;
        this.isPressed = false;
    }

    public void press() {
        this.isPressed = true;
        System.out.println("Button " + label + " pressed.");
    }

    public void reset() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public String getLabel() {
        return label;
    }
}
