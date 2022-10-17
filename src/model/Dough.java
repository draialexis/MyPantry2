package model;

import utils.DoughColor;

import java.io.Serializable;

public class Dough implements Serializable {
    private final DoughColor color;

    public Dough(DoughColor color) {
        this.color = color;
    }

    public DoughColor getColor() {
        return color;
    }
}
