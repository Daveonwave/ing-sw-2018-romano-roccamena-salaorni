package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Card;

public class CardView extends ObjectView{
    //Veduta di una carta

    private Card card;

    //Costruttori
    public CardView(Button button, ImageView imageView, Card card) {
        super(button, imageView);
        this.card = card;
    }

    //Setter/Getter
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Image imagePath() {
        return null;
    }
}
