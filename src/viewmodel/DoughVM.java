package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Dough;
import utils.ColorConverter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DoughVM implements PropertyChangeListener {
    private final Dough model;

    private final ObjectProperty<Color> color = new SimpleObjectProperty<>();
    public Color getColor() {return color.get();}
    public ObjectProperty<Color> colorProperty() {return color;}
    public void setColor(Color color) {this.color.set(color);}

    public DoughVM(Dough model) {
        this((Object) model);
    }

    /**
     * constructs a DoughVM from a Dough
     *
     * @param o needs to be an instance of Dough
     */
    public DoughVM(Object o) {
        if (o instanceof Dough) {
            this.model = (Dough) o;

            // load into self
            setColor(ColorConverter.toVM(model.getColor()));

            // subscribe
            model.addListener(this);

            // update ("set")
            colorProperty().addListener(__ -> {
                if (!ColorConverter.areEqual(model.getColor(), this.getColor())) {
                    model.setColor(ColorConverter.toModel(this.getColor()));
                }
            });
        }
        else {
            throw new IllegalArgumentException("only dough please");
        }
    }

    public DoughVM(Color color) {
        this(new Dough(ColorConverter.toModel(color)));
    }

    public Dough getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Dough.PROP_DOUGH_COLOR)) {
            setColor(ColorConverter.toVM(evt.getNewValue()));
        }
    }
}
