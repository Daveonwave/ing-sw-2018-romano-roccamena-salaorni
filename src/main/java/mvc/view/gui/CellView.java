package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Cell;


public class CellView{
    //Veduta di una cella

    private Cell cell;
    private ImageView imageView;

    //Costruttori
    public CellView(Cell cell,ImageView imageView) {
        this.cell = cell;
        this.imageView = imageView;
    }

    //Setter/Getter
    public Cell getCell() {
        return cell;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
