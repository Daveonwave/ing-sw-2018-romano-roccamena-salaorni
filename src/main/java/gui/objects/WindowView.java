package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Window;


/**
 * view of a window
 */
public class WindowView extends ObjectView{
    //Veduta finestra

    private Window window;
    private CellView[][] cells;

    /**
     *
     * @param imageView image view of this window view
     * @param window window associated to this window view
     * @param cells cells of the window
     */
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

    /**
     * gets the corresponding image of the window based on its name
     * @return image of this window
     */
    public Image imagePath() {
        return new Image(getClass().getResourceAsStream("images/windows/" + window.getName() + ".png"));
    }
}
