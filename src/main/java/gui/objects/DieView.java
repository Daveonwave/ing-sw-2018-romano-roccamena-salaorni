package gui.objects;

import gui.FXGuiConstant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.exceptions.AppViewException;
import mvc.model.objects.Die;

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
                // eccezione gestita
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
    public Image imagePath() throws AppViewException {
        String path = "images/dice/";
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

