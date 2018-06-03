package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Card;

public class CardView extends ObjectView{
    //Veduta di una carta

    private Card card;

    //Costruttori
    public CardView(ImageView imageView, Card card) {
        super(imageView);
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
        String path = "";
        switch (card.getName()){
            case "sfumature blu":
                path = "sfumatureblu.PNG";
                break;
            case "sfumature gialle":
                path = "sfumaturegialle.PNG";
                break;
            case "sfumature viola":
                path = "sfumatureviola.PNG";
                break;
            case "sfumature verdi":
                path = "sfumatureverdi.PNG";
                break;
            case "sfumature rosse":
                path = "sfumaturerosse.PNG";
                break;
            case "diagonali colorate":
                path = "diagonalicolorate.PNG";
                break;
            case "colori diversi - colonna":
                path = "coloridiversi-colonna.PNG";
                break;
            case "colori diversi - riga":
                path = "coloridiversi-riga.PNG";
                break;
            case "sfumature diverse - colonna":
                path = "sfumaturediverse-colonna.PNG";
                break;
            case "sfumature diverse - riga":
                path = "sfumaturediverse-riga.PNG";
                break;
            case "sfumature diverse":
                path = "sfumaturediverse.PNG";
                break;
            case "sfumature chiare":
                path = "sfumaturechiare.PNG";
                break;
            case "sfumature medie":
                path = "sfumaturemedie.PNG";
                break;
            case "sfumature scure":
                path = "sfumaturescure.PNG";
                break;
            case "varietà di colore":
                path = "varietadicolore.PNG";
                break;
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
