package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Card;

/**
 * view of an objective card
 */
public class ObjectiveCardView extends ObjectView {

    private Card card;

    /**
     *
     * @param imageView image view of this objective card
     * @param card card associated to this card view
     */
    public ObjectiveCardView(ImageView imageView, Card card) {
        super(imageView);
        this.card = card;
        this.getImageView().setImage(imagePath());
    }

    //Getter/Setter
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


    /**
     * gets the corresponding image based on the name of the objective card
     * @return image of the objective card
     */
    public Image imagePath() {
        return new Image(getClass().getResourceAsStream("images/objective/" + card.getName() + ".png"));
    }
}
