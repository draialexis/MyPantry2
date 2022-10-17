package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.Pasta;
import utils.ColorConverter;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PastaVM implements PropertyChangeListener {

    private final Pasta model;

    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();

    public Color getColor() {return color.get();}
    public ObjectProperty<Color> colorProperty() {return color;}
    public void setColor(Color color) {this.color.set(color);}


    private final StringProperty type = new SimpleStringProperty();
    public String getType() {return type.get();}
    public StringProperty typeProperty() {return type;}
    public void setType(String type) {this.type.set(type);}

    private final ObservableList<DoughVM> doughsObs = FXCollections.observableArrayList();
    private final ListProperty<DoughVM> doughs = new SimpleListProperty<>(doughsObs);
    public ObservableList<DoughVM> getDoughs() {return FXCollections.unmodifiableObservableList(doughs.get());}
    public ReadOnlyListProperty<DoughVM> doughsProperty() {return doughs;}

    public PastaVM(Pasta model) {
        this((Object) model);
    }

    public PastaVM() {
        this(new Pasta());
    }

    /**
     * constructs a PastaVM from a Pasta
     * @param o needs to be an instance of Pasta
     */
    public PastaVM(Object o) {
        if (o instanceof Pasta) {
            model = (Pasta) o;

            // load into self
            setColor(ColorConverter.toVM(model.getColor()));
            setType(model.getClass().getSimpleName());
            model.getDoughs().forEach(d -> doughsObs.add(new DoughVM(d)));

            // subscribe
            model.addListener(this);
        } else {
            throw new IllegalArgumentException("only Pasta please");
        }
    }

    public Pasta getModel() {
        return model;
    }



    public void addDoughVM(DoughVM toAdd) {
        if(toAdd != null) {
            model.addDough(toAdd.getModel());
        }
    }

    public void removeDoughVM(DoughVM toRemove) {
        if(toRemove != null) {
            model.removeDough(toRemove.getModel());
        }
    }

    public void updateDough(DoughVM before, DoughVM after) {
        if(before != null && after != null) {
            model.updateDough(before.getModel(), after.getModel());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(Pasta.PROP_PASTA_COMPUTE_COLOR)){
            if(!ColorConverter.areEqual(evt.getNewValue(), getColor())) {
                setColor(ColorConverter.toVM(evt.getNewValue()));
            }
        }
        if(evt.getPropertyName().equals(Pasta.PROP_PASTA_ADD_DOUGH)) {
            doughsObs.add(
                    ((IndexedPropertyChangeEvent)evt).getIndex(),
                    new DoughVM(evt.getNewValue())
            );
        }
        if(evt.getPropertyName().equals(Pasta.PROP_PASTA_RMV_DOUGH)) {
            doughsObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }
}
