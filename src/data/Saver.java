package data;

import viewmodel.PantryVM;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Saver implements Savable {
    @Override
    public void save(PantryVM viewmodel) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(viewmodel.getModel());
        }
    }
}
