package mvc.view.gui;

import mvc.model.objects.Die;
import javafx.scene.control.Button;

public class DieView {
    //View gui dado

    private Die die;
    private String imagePath;
    private Button button;

    //Costruttori
    public DieView(Die die, String imagePath, Button button) {
        this.die = die;
        this.imagePath = imagePath;
        this.button = button;
    }

    //Setter/Getter
    public void setDie(Die die) {
        this.die = die;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setButton(Button button) {
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
}
