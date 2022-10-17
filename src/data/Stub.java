package data;

import javafx.scene.paint.Color;
import viewmodel.DoughVM;
import viewmodel.PantryVM;
import viewmodel.PastaVM;

public class Stub implements Loadable {
    @Override
    public PantryVM load() {

        PantryVM result = new PantryVM();

        PastaVM pastaVM1 = new PastaVM();
        pastaVM1.addDoughVM(new DoughVM(new Color(0.9, 0.8, 0.7, 1.0)));
        result.addPastaVM(pastaVM1);

        PastaVM pastaVM2 = new PastaVM();
        pastaVM2.addDoughVM(new DoughVM(new Color(0.9, 0.8, 0.7, 1.0)));
        pastaVM2.addDoughVM(new DoughVM(new Color(1.0, 1.0, 1.0, 1.0)));
        result.addPastaVM(pastaVM2);

        PastaVM pastaVM3 = new PastaVM();
        pastaVM3.addDoughVM(new DoughVM(new Color(0.9, 0.9, 0.9, 1.0)));
        pastaVM3.addDoughVM(new DoughVM(new Color(0.6, 0.2, 0.3, 1.0)));
        result.addPastaVM(pastaVM3);

        PastaVM pastaVM4 = new PastaVM();
        pastaVM3.addDoughVM(new DoughVM(new Color(0.9, 0.9, 0.9, 1.0)));
        pastaVM4.addDoughVM(new DoughVM(new Color(0.7, 0.3, 0.5, 1.0)));
        result.addPastaVM(pastaVM4);

        PastaVM pastaVM5 = new PastaVM();
        pastaVM5.addDoughVM(new DoughVM(new Color(0.1, 0.1, 0.1, 1.0)));
        pastaVM5.addDoughVM(new DoughVM(new Color(0.5, 1.0, 0.5, 1.0)));
        pastaVM3.addDoughVM(new DoughVM(new Color(0.9, 0.9, 0.9, 1.0)));
        result.addPastaVM(pastaVM5);

        return result;
    }
}
