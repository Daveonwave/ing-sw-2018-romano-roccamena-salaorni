package gui.objects;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Cell;

import java.io.Serializable;


public class CellView implements Serializable{
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
