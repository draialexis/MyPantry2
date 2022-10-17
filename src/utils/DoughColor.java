package utils;

import java.io.Serializable;

public record DoughColor(double r, double g, double b) implements Serializable {
    /**
     * @param r red 0-1
     * @param g green 0-1
     * @param b blue 0-1
     */
    public DoughColor {
    }

    public double getR() {
        return r;
    }

    public double getB() {
        return b;
    }

    public double getG() {
        return g;
    }

}
