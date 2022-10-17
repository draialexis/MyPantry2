package model;

import utils.DoughColor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pasta implements Serializable {
    public static final String PROP_PASTA_COMPUTE_COLOR = "model.pasta.computeColor";
    public static final String PROP_PASTA_ADD_DOUGH = "model.pasta.addDough";
    public static final String PROP_PASTA_RMV_DOUGH = "model.pasta.removeDough";

    private static final DoughColor DEFAULT_DOUGH_COLOR = new DoughColor(0.0, 0.0, 0.0);

    private transient PropertyChangeSupport support = null;

    private final List<Dough> doughs = new ArrayList<>();

    private DoughColor color = DEFAULT_DOUGH_COLOR;

    public DoughColor getColor() {
        return color;
    }

    public List<Dough> getDoughs() {
        return Collections.unmodifiableList(doughs);
    }

    /**
     * will likely change the overall color
     */
    public void addDough(Dough toAdd) {
        if (toAdd != null) {
            int i = 0;
            doughs.add(i, toAdd);
            getSupport().fireIndexedPropertyChange(PROP_PASTA_ADD_DOUGH,
                                                   i,
                                                   getDoughs().size() > i + 1 ? getDoughs().get(i + 1) : null,
                                                   toAdd);
            computeColor();
        }
    }

    /**
     * will likely change the overall color
     */
    public void removeDough(Dough toRemove) {
        if (toRemove != null) {
            int i = getDoughs().indexOf(toRemove);
            doughs.remove(toRemove);
            getSupport().fireIndexedPropertyChange(PROP_PASTA_RMV_DOUGH,
                                                   i,
                                                   toRemove,
                                                   getDoughs().size() > i ? getDoughs().get(i) : null);
            computeColor();
        }
    }

    public void updateDough(Dough before, Dough after) {
        if (before != null && after != null) {
            removeDough(before);
            addDough(after);
        }
    }

    private void computeColor() {
        int n = doughs.size();
        DoughColor oldV = color;
        if (doughs.size() < 1) {
            color = DEFAULT_DOUGH_COLOR;
        }
        else {
            double r = color.getR();
            double g = color.getG();
            double b = color.getB();

            for (Dough dough : doughs) {
                DoughColor doughColor = dough.getColor();
                r += doughColor.getR();
                g += doughColor.getG();
                b += doughColor.getB();
            }

            r /= n;
            g /= n;
            b /= n;

            color = new DoughColor(Math.max(0.0, Math.min(r, 1.0)),
                                   Math.max(0.0, Math.min(g, 1.0)),
                                   Math.max(0.0, Math.min(b, 1.0)));
        }
        getSupport().firePropertyChange(PROP_PASTA_COMPUTE_COLOR, oldV, color);
    }

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }
}
