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

    /**
     * @param rInt red 0-255
     * @param gInt green 0-255
     * @param bInt blue 0-255
     */
    public DoughColor(int rInt, int gInt, int bInt) {
        this(rInt / 255.0, gInt / 255.0, bInt / 255.0);
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
