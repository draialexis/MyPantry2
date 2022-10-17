package utils;

import java.io.Serializable;

public class DoughColor implements Serializable {
    private final double r;
    private final double b;
    private final double g;

    /**
     * @param r red 0-1
     * @param g green 0-1
     * @param b blue 0-1
     */
    public DoughColor(double r, double g, double b) {
        this.r = r;
        this.b = b;
        this.g = g;
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
