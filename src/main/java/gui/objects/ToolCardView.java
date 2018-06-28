package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.ToolCard;

/**
 * view of a tool card
 */
public class ToolCardView extends ObjectView{

    private ToolCard toolCard;

    //Costruttori
    public ToolCardView(ImageView imageView, ToolCard toolCard) {
        super(imageView);
        this.toolCard = toolCard;
        this.getImageView().setImage(imagePath());
    }

    //Setter/Getter
    public ToolCard getToolCard() {
        return toolCard;
    }

    public void setToolCard(ToolCard toolCard) {
        this.toolCard = toolCard;
    }

    /**
     * gets the corresponding image of the tool card based in its name
     * @return image of this tool card
     */
    public Image imagePath() {
        return new Image(getClass().getResourceAsStream("images/tools/"+ toolCard.getName() + ".png"));
    }
}
