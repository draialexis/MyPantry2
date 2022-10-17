package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Dough;
import utils.ColorConverter;

public class DoughVM {
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
     * @param o needs to be an instance of Dough
     */
    public DoughVM(Object o) {
        if (o instanceof Dough) {
            this.model = (Dough) o;

            // TODO subscribe to dough and update it at need?

        } else {
            throw new IllegalArgumentException("only dough please");
        }
    }

    public DoughVM(Color color) {
        this(new Dough(ColorConverter.toModel(color)));
    }

    public Dough getModel() {
        return model;
    }
}
