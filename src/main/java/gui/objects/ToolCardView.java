package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.ToolCard;

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

    //Ottiene immagine
    public Image imagePath() {
        String path = "";
        switch (toolCard.getName()){
            case "alesatore per lamina di rame":
                path = "alesatoreperlaminadirame.PNG";
                break;
            case "diluente per pasta calda":
                path = "diluenteperpastacalda.PNG";
                break;
            case "lathekin":
                path = "lathekin.PNG";
                break;
            case "martelletto":
                path = "martelletto.PNG";
                break;
            case "pennello per eglomise":
                path = "pennellopereglomise.PNG";
                break;
            case "pennello per pasta calda":
                path = "pennelloperpastacalda.PNG";
                break;
            case "pinza sgrossatrice":
                path = "pinzasgrossatrice.PNG";
                break;
            case "riga di sughero":
                path = "rigadisughero.PNG";
                break;
            case "taglierina cirolare":
                path = "taglierinacircolare.PNG";
                break;
            case "taglierina manuale":
                path = "taglierinamanuale.PNG";
                break;
            case "tampone diamantato":
                path = "tamponediamantato.PNG";
                break;
            case "tenaglia a rotelle":
                path = "tenagliaarotelle.PNG";
                break;
        }

        return new Image(getClass().getResourceAsStream(path));
    }
}
