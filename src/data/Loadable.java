package data;

import viewmodel.PantryVM;

import java.io.IOException;

public interface Loadable {
    PantryVM load() throws IOException, ClassNotFoundException;
}
