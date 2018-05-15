package mvc.view.gui;

import javafx.scene.control.Button;
import mvc.model.objects.Card;

public class CardView {
    private Card card;
    private String imagePath;
    private Button button;

    public CardView(Card card, String imagePath, Button button) {
        this.card = card;
        this.imagePath = imagePath;
        this.button = button;
    }

    public Card getCard() {
        return card;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Button getButton() {
        return button;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
