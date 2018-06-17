package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Window;

public class WindowView extends ObjectView{
    //Veduta finestra

    private Window window;
    private CellView[][] cells;

    //costruttori
    public WindowView(ImageView imageView, Window window, CellView[][] cells) {
        super(imageView);
        this.window = window;
        this.cells = cells;
        this.getImageView().setImage(imagePath());
    }

    //Setter/Getter
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
        return new Image(getClass().getResourceAsStream("images/windows/" + window.getName() + ".png"));
    }
}
