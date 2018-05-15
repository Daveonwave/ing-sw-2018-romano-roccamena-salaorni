package mvc.view.gui;

import mvc.model.objects.Die;
import javafx.scene.control.Button;
import java.awt.*;

public class DieView {

    private Die die;
    private String imagePath;
    private Button button;


    public DieView(Die die, String imagePath, Button button) {
        this.die = die;
        this.imagePath = imagePath;
        this.button = button;
    }

    public Die getDie() {
        return die;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Button getButton() {
        return button;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
