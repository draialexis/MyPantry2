package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pantry;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PantryVM implements PropertyChangeListener {

    private final Pantry model;

    public Pantry getModel() {
        return model;
    }

    private final ObservableList<PastaVM> pastaObs = FXCollections.observableArrayList();
    private final ListProperty<PastaVM> pasta = new SimpleListProperty<>(pastaObs);
    public ObservableList<PastaVM> getPasta() { return FXCollections.unmodifiableObservableList(pasta.get()); }
    public ReadOnlyListProperty<PastaVM> pastaProperty() { return pasta; }

    public PantryVM(Object o) {
        if(o instanceof Pantry){
            this.model = (Pantry) o;

            // load into self
            this.model.getPasta().forEach(p -> pastaObs.add(new PastaVM(p)));

            // subscribe
            this.model.addListener(this);
        } else throw new IllegalArgumentException("only pantries please");
    }
    public PantryVM() {
        this(new Pantry());
    }

    public PantryVM(Pantry model) {
        this((Object) model);
    }

    public void addPastaVM(PastaVM toAdd) {
        if(toAdd != null) {
            model.addPasta(toAdd.getModel());
        }
    }

    public void removePastaVM(PastaVM toRemove) {
        if(toRemove != null) {
            model.removePasta(toRemove.getModel());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(Pantry.PROP_PANTRY_ADD_PASTA)) {
            pastaObs.add(
                    ((IndexedPropertyChangeEvent)evt).getIndex(),
                    new PastaVM(evt.getNewValue())
            );
        }
        if(evt.getPropertyName().equals(Pantry.PROP_PANTRY_RMV_PASTA)) {
            pastaObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }
}
