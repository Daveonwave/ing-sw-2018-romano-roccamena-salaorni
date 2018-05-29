package mvc.view.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Die;
import javafx.scene.control.Button;

public class DieView extends ObjectView{
    //View gui dado

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
        return null;
    }
}
