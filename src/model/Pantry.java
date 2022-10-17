package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pantry implements Serializable {

    public static final String PROP_PANTRY_ADD_PASTA = "model.pantry.addPasta";
    public static final String PROP_PANTRY_RMV_PASTA = "model.pantry.removePasta";

    private PropertyChangeSupport support;

    private final List<Pasta> pasta = new ArrayList<>();

    public List<Pasta> getPasta() {
        return Collections.unmodifiableList(pasta);
    }

    public void addPasta(Pasta toAdd) {
        if (toAdd != null) {
            int i = 0;
            pasta.add(i, toAdd);
            getSupport().fireIndexedPropertyChange(PROP_PANTRY_ADD_PASTA,
                                                   i,
                                                   getPasta().size() > i + 1 ? getPasta().get(i + 1) : null,
                                                   toAdd);
        }
    }

    public void removePasta(Pasta toRemove) {
        if (toRemove != null) {
            int i = getPasta().indexOf(toRemove);
            pasta.remove(toRemove);
            getSupport().fireIndexedPropertyChange(PROP_PANTRY_RMV_PASTA,
                                                   i,
                                                   toRemove,
                                                   getPasta().size() > i ? getPasta().get(i) : null);
        }
    }

    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    private PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }
}
