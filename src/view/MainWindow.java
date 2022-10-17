package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import viewmodel.PantryVM;
import viewmodel.PastaVM;

public class MainWindow {
    @FXML
    private Label pastaTypeLbl;

    @FXML
    private Rectangle pastaClrRect;

    @FXML
    private ListView<PastaVM> pastaLV;

    @FXML
    private void clickAddPasta(ActionEvent evt) {

    }

    @FXML
    private void clickRemovePasta(ActionEvent evt) {
        viewmodel.removePastaVM(pastaLV.getSelectionModel().getSelectedItem());
        pastaClrRect.setFill(new Color(1.0, 1.0, 1.0, 1.0));
        pastaTypeLbl.setText("");
    }

    @FXML
    private void clickQuit(ActionEvent evt) {
        pastaTypeLbl.getScene().getWindow().hide();
    }

    private final PantryVM viewmodel;

    public MainWindow(PantryVM viewmodel) {
        this.viewmodel = viewmodel;
    }

    @FXML
    private void initialize() {
        pastaLV.itemsProperty().bind(viewmodel.pastaProperty());
        pastaLV.setCellFactory(__ -> new PastaCell());
        pastaLV.getSelectionModel().selectedItemProperty().addListener((__, oldV, newV) -> {
            if (oldV != null) {
                pastaTypeLbl.textProperty().unbind();
                pastaClrRect.fillProperty().unbind();
            }
            if (newV != null) {
                pastaTypeLbl.textProperty().bind(newV.typeProperty());
                pastaClrRect.fillProperty().bind(newV.colorProperty());
            }
        });
    }
}
