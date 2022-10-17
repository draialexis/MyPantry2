package utils;

import javafx.scene.paint.Color;

public class ColorConverter {

    public static DoughColor toModel(Color VM) {
        return new DoughColor(VM.getRed(), VM.getGreen(), VM.getBlue());
    }

    public static Color toVM(DoughColor model) {
        return new Color(model.getR(), model.getG(), model.getB(), 1.0);
    }

    /**
     * @return NULL if not given a DoughColor, else a javafx Color
     */
    public static Color toVM(Object o) {
        if (o instanceof DoughColor) {
            return toVM((DoughColor) o);
        }
        else {return null;}
    }

    /**
     * @return NULL if not given a javafx Color, else a DoughColor
     */
    public static DoughColor toModel(Object o) {
        if (o instanceof Color) {
            return toModel((Color) o);
        }
        else {return null;}
    }

    public static boolean areEqual(DoughColor doughColor, Color JFXColor) {
        return JFXColor.getRed() == doughColor.getR()
               && JFXColor.getBlue() == doughColor.getB()
               && JFXColor.getGreen() == doughColor.getG();
    }

    public static boolean areEqual(Object o, Color JFXColor) {
        if(o instanceof DoughColor){
            return areEqual((DoughColor) o, JFXColor);
        } else throw new IllegalArgumentException("only doughcolors please");
    }
}
