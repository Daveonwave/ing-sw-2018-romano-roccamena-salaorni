package mvc.view.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Die;
import javafx.scene.control.Button;
import mvc.model.objects.GameConstants;

import java.util.List;

public class DieView extends ObjectView {
    //Veduta di un dado

    private Die die;

    //Costruttori
    public DieView(ImageView imageView, Die die) {
        super(imageView);
        this.die = die;
    }

    //Setter/Getter
    public void setDie(Die die) {
        this.die = die;
    }

    public Die getDie() {
        return die;
    }

    public Image imagePath() {
        String path = "";
        switch (this.die.getShade()){
            case 1:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow1.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue1.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple1.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green1.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red1.png";
                break;

            case 2:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow2.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue2.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple2.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green2.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red2.png";
                break;

            case 3:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow3.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue3.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple3.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green3.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red3.png";
                break;

            case 4:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow4.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue4.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple4.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green4.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red4.png";
                break;

            case 5:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow5.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue5.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple5.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green5.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red5.png";
                break;

            case 6:
                if(die.getColor().equals(GameConstants.YELLOW)) path = "yellow6.png";
                if(die.getColor().equals(GameConstants.BLUE)) path = "blue6.png";
                if(die.getColor().equals(GameConstants.PURPLE)) path = "purple6.png";
                if(die.getColor().equals(GameConstants.GREEN)) path = "green6.png";
                if(die.getColor().equals(GameConstants.RED)) path = "red6.png";
                break;
            }
            return new Image(getClass().getResourceAsStream(path));
        }

}

