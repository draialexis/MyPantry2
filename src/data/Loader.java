package data;

import viewmodel.PantryVM;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loader implements Loadable {

    @Override
    public PantryVM load() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))){
            return new PantryVM(ois.readObject());
        }
    }
}
