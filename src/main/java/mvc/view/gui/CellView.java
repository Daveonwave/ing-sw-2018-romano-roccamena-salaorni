package mvc.view.gui;

import javafx.scene.control.Button;
import mvc.model.objects.Cell;

import java.awt.*;

public class CellView {

    private Cell cell;
    private Button button;

    public CellView(Cell cell, Button button) {
        this.cell = cell;
        this.button = button;
    }

    public Cell getCell() {
        return cell;
    }

    public Button getButton() {
        return button;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
