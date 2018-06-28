package gui.objects;

import gui.FXGuiConstant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.exceptions.AppViewException;
import mvc.model.objects.Die;
import mvc.model.objects.enums.DieColor;

/**
 * view of a die
 */
public class DieView extends ObjectView {
    //Veduta di un dado

    private Die die;

    //Costruttori
    public DieView(ImageView imageView, Die die) {
        super(imageView);
        this.die = die;
        if(imageView != null) {
            try {
                this.getImageView().setImage(imagePath());
            } catch (AppViewException e) {
                e.printStackTrace();
            }
        }
    }

    //Setter/Getter
    public void setDie(Die die) {
        this.die = die;
    }
    public Die getDie() {
        return die;
    }

    /**
     * gets the corresponding image of the die base on its color and shade
     * @return image of the die
     */
    public Image imagePath() throws AppViewException{
        String path = "images/dice/";
        switch (this.die.getShade()){
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
            default: throw new AppViewException(FXGuiConstant.IMAGE_EXCEPTION_MESSAGE);
            }
            return new Image(getClass().getResourceAsStream(path));
        }

}

