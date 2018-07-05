package gui.objects;

import gui.FXGuiConstant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.exceptions.AppViewException;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;

import java.io.Serializable;

/**
 * view of a cell
 */
public class CellView implements Serializable {
    //Veduta di una cella

    private Cell cell;
    private ImageView imageView;

    /**
     *
     * @param cell cell associated to this cell view
     * @param imageView image view of the cell view
     */
    public CellView(Cell cell, ImageView imageView) {
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

    /**
     * loads the corresponding image based on the shade and colour of the dice this cell contains
     *
     * @return corresponding image
     */
    public Image imagePath() throws AppViewException {
        String path = "images/dice/";
        Die die = this.getCell().getDie();
        switch (die.getColor()) {
            case BLUE:
                path += "blue";
                break;
            case YELLOW:
                path += "yellow";
                break;
            case PURPLE:
                path += "purple";
                break;
            case RED:
                path += "red";
                break;
            case GREEN:
                path += "green";
                break;
            default:
                throw new AppViewException(FXGuiConstant.IMAGE_EXCEPTION_MESSAGE);

        }
        path += die.getShade() + ".png";
        return new Image(getClass().getResourceAsStream(path));
    }
}
