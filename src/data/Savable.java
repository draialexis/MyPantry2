package data;

import viewmodel.PantryVM;

import java.io.IOException;

public interface Savable {
    void save(PantryVM viewmodel) throws IOException;
}
