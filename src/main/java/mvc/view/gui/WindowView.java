package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Window;

public class WindowView extends ObjectView{

    private Window window;
    private CellView[][] cells;

    //costruttori
    public WindowView(ImageView imageView, Window window, CellView[][] cells) {
        super(imageView);
        this.window = window;
        this.cells = cells;
    }

    //setter/getter
    public Window getWindow() {
        return window;
    }

    public CellView[][] getCells() {
        return cells;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setCells(CellView[][] cells) {
        this.cells = cells;
    }


    public Image imagePath() {
        return null;
    }
}
