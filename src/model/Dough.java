package model;

import utils.DoughColor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Dough implements Serializable {
    public static final String PROP_DOUGH_COLOR = "model.dough.color";

    private transient PropertyChangeSupport support = null;

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    private DoughColor color;

    public Dough(DoughColor color) {
        this.color = color;
    }

    public DoughColor getColor() {
        return color;
    }

    public void setColor(DoughColor color) {
        DoughColor oldV = this.color;
        this.color = color;
        getSupport().firePropertyChange(PROP_DOUGH_COLOR, oldV, this.color);
    }

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

}
