package gui.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Card;

public class ObjectiveCardView extends ObjectView {

    private Card card;

    //Costruttori
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


    //Ottiene immagine
    public Image imagePath() {
        String path = "images/objective/";
        switch (card.getName()){
            case "sfumature blu":
                path += "sfumatureblu.PNG";
                break;
            case "sfumature gialle":
                path += "sfumaturegialle.PNG";
                break;
            case "sfumature viola":
                path += "sfumatureviola.PNG";
                break;
            case "sfumature verdi":
                path += "sfumatureverdi.PNG";
                break;
            case "sfumature rosse":
                path += "sfumaturerosse.PNG";
                break;
            case "diagonali colorate":
                path += "diagonalicolorate.PNG";
                break;
            case "colori diversi - colonna":
                path += "coloridiversi-colonna.PNG";
                break;
            case "colori diversi - riga":
                path += "coloridiversi-riga.PNG";
                break;
            case "sfumature diverse - colonna":
                path += "sfumaturediverse-colonna.PNG";
                break;
            case "sfumature diverse - riga":
                path += "sfumaturediverse-riga.PNG";
                break;
            case "sfumature diverse":
                path += "sfumaturediverse.PNG";
                break;
            case "sfumature chiare":
                path += "sfumaturechiare.PNG";
                break;
            case "sfumature medie":
                path += "sfumaturemedie.PNG";
                break;
            case "sfumature scure":
                path += "sfumaturescure.PNG";
                break;
            case "varietà di colore":
                path += "varietadicolore.PNG";
                break;
        }
        return new Image(getClass().getResourceAsStream(path));
    }
}
