package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import viewmodel.DoughVM;
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
    private ListView<DoughVM> doughsLV;

    @FXML
    private ColorPicker doughClrPicker;

    @FXML
    private void clickAddDough() {
        PastaVM toMod = pastaLV.getSelectionModel().getSelectedItem();
        DoughVM toAdd = new DoughVM(doughClrPicker.getValue());
        toMod.addDoughVM(toAdd);
    }

    @FXML
    private void clickRemoveDough() {
        PastaVM toMod = pastaLV.getSelectionModel().getSelectedItem();
        DoughVM toRemove = doughsLV.getSelectionModel().getSelectedItem();
        toMod.removeDoughVM(toRemove);
    }

    @FXML
    private void clickUpdateDough() {
        PastaVM toMod = pastaLV.getSelectionModel().getSelectedItem();
        DoughVM before = doughsLV.getSelectionModel().getSelectedItem();
        toMod.updateDough(before, new DoughVM(doughClrPicker.getValue()));
    }

    @FXML
    private void clickAddPasta() {
        viewmodel.addPastaVM(new PastaVM());
    }

    @FXML
    private void clickRemovePasta(ActionEvent evt) {
        viewmodel.removePastaVM(pastaLV.getSelectionModel().getSelectedItem());
        pastaClrRect.setFill(null);
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
        initPastaLV();

        doughsLV.setCellFactory(__ -> new DoughCell());
    }

    private void initPastaLV() {
        pastaLV.getSelectionModel().selectedItemProperty().addListener((__, oldV, newV) -> {
            if (oldV != null) {
                pastaTypeLbl.textProperty().unbind();
                pastaClrRect.fillProperty().unbind();
                doughsLV.itemsProperty().unbind();
            }
            if (newV != null) {
                pastaTypeLbl.textProperty().bind(newV.typeProperty());
                pastaClrRect.fillProperty().bind(newV.colorProperty());
                doughsLV.itemsProperty().bind(newV.doughsProperty());
            }
        });
    }
}
