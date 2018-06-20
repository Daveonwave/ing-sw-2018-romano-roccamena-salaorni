package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Cell;
import mvc.model.objects.Die;
import mvc.model.objects.GameConstants;

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


    public Image imagePath() {
        String path = "images/dice/";
        Die die = this.getCell().getDie();
        switch (die.getShade()){
            case 1:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow1.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue1.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple1.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green1.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red1.png";
                break;

            case 2:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow2.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue2.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple2.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green2.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red2.png";
                break;

            case 3:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow3.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue3.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple3.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green3.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red3.png";
                break;

            case 4:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow4.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue4.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple4.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green4.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red4.png";
                break;

            case 5:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow5.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue5.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple5.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green5.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red5.png";
                break;

            case 6:
                if(die.getColor().equals(GameConstants.YELLOW)) path += "yellow6.png";
                if(die.getColor().equals(GameConstants.BLUE)) path += "blue6.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path += "purple6.png";
                if(die.getColor().equals(GameConstants.GREEN)) path += "green6.png";
                if(die.getColor().equals(GameConstants.RED)) path += "red6.png";
                break;
        }
        return new Image(getClass().getResourceAsStream(path));
    }
}
