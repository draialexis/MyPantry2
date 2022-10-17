package view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import viewmodel.PastaVM;

public class PastaCell extends ListCell<PastaVM> {
    @Override
    protected void updateItem(PastaVM item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            BorderPane pane = new BorderPane();
            Rectangle rect = new Rectangle();
            Label label = new Label();

            rect.setWidth(10.0);
            rect.setHeight(10.0);
            rect.fillProperty().bind(item.colorProperty());
            label.textProperty().bind(item.typeProperty());

            pane.setLeft(label);
            pane.setRight(rect);

            setGraphic(pane);
        }
        else {
            setGraphic(null);
        }
    }
}
