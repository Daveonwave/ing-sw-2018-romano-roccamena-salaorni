package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Window;

public class WindowView extends ObjectView{
    //Veduta finestra

    private Window window;
    private CellView[][] cells;
    private ImageView container;

    //Costruttori
    public WindowView(Button button, ImageView imageView, Window window, CellView[][] cells, ImageView container) {
        super(button, imageView);
        this.window = window;
        this.cells = cells;
        this.container = container;
    }

    //Setter/Getter
    public Window getWindow() {
        return window;
    }
    public CellView[][] getCells() {
        return cells;
    }
    public ImageView getContainer() {
        return container;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    public void setCells(CellView[][] cells) {
        this.cells = cells;
    }
    public void setContainer(ImageView container) {
        this.container = container;
    }

    public Image imagePath() {
        return null;
    }
}
