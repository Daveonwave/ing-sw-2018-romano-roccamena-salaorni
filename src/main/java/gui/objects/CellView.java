package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;
import mvc.model.objects.enums.DieColor;

import java.io.Serializable;

/**
 * view of a cell
 */
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

    /**
     * loads the corresponding image based on the shade and colour of the dice this cell contains
     * @return corresponding image
     */
    public Image imagePath() {
        String path = "images/dice/";
        Die die = this.getCell().getDie();
        switch (die.getShade()){
            case 1:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow1.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue1.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple1.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green1.png";
                if(die.getColor().equals(DieColor.RED)) path += "red1.png";
                break;

            case 2:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow2.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue2.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple2.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green2.png";
                if(die.getColor().equals(DieColor.RED)) path += "red2.png";
                break;

            case 3:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow3.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue3.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple3.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green3.png";
                if(die.getColor().equals(DieColor.RED)) path += "red3.png";
                break;

            case 4:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow4.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue4.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple4.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green4.png";
                if(die.getColor().equals(DieColor.RED)) path += "red4.png";
                break;

            case 5:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow5.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue5.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple5.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green5.png";
                if(die.getColor().equals(DieColor.RED)) path += "red5.png";
                break;

            case 6:
                if(die.getColor().equals(DieColor.YELLOW)) path += "yellow6.png";
                if(die.getColor().equals(DieColor.BLUE)) path += "blue6.png";
                if(die.getColor().equals(DieColor.PURPLE)) path += "purple6.png";
                if(die.getColor().equals(DieColor.GREEN)) path += "green6.png";
                if(die.getColor().equals(DieColor.RED)) path += "red6.png";
                break;
        }
        return new Image(getClass().getResourceAsStream(path));
    }
}
