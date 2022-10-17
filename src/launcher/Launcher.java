package launcher;

import data.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainWindow;
import viewmodel.PantryVM;

import java.io.IOException;

public class Launcher extends Application {

    PantryVM viewmodel = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Loadable loader;
        try {
            loader = new Loader();
            viewmodel = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            loader = new Stub();
            viewmodel = loader.load();
        }
        if (viewmodel == null) {
            viewmodel = new PantryVM();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        fxmlLoader.setController(new MainWindow(viewmodel));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            Savable saver = new Saver();
            saver.save(viewmodel);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            super.stop();
        }
    }
}
