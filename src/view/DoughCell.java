package view;

import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import viewmodel.DoughVM;

public class DoughCell extends ListCell<DoughVM> {
    @Override
    protected void updateItem(DoughVM item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            BorderPane pane = new BorderPane();
            Rectangle rect = new Rectangle();

            rect.setWidth(80.0);
            rect.setHeight(10.0);
            rect.fillProperty().bind(item.colorProperty());

            pane.setRight(rect);

            setGraphic(pane);
        }
        else {
            setGraphic(null);
        }
    }
}
