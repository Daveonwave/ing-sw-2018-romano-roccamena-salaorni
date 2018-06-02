package mvc.view.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mvc.model.objects.Card;

public class CardView extends ObjectView{
    //View gui di una carta

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
                path = "sfumatureblu.png";
                break;
            case "sfumature gialle":
                path = "sfumaturegialle.png";
                break;
            case "sfumature viola":
                path = "sfumatureviola.png";
                break;
            case "sfumature verdi":
                path = "sfumatureverdi.png";
                break;
            case "sfumature rosse":
                path = "sfumaturerosse.png";
                break;
            case "diagonali colorate":
                path = "diagonalicolorate.png";
                break;
            case "colori diversi - colonna":
                path = "coloridiversi-colonna.png";
                break;
            case "colori diversi - riga":
                path = "coloridiversi-riga.png";
                break;
            case "sfumature diverse - colonna":
                path = "sfumaturediverse-colonna.png";
                break;
            case "sfumature diverse - riga":
                path = "sfumaturediverse-riga.png";
                break;
            case "sfumature diverse":
                path = "sfumaturediverse.png";
                break;
            case "sfumature chiare":
                path = "sfumaturechiare.png";
                break;
            case "sfumature medie":
                path = "sfumaturemedie.png";
                break;
            case "sfumature scure":
                path = "sfumaturescure.png";
                break;
            case "varietà di colore":
                path = "varietadicolore.png";
                break;
            case "alesatore per lamina di rame":
                path = "alesatoreperlaminadirame.png";
                break;
            case "diluente per pasta calda":
                path = "diluenteperpastacalda.png";
                break;
            case "lathekin":
                path = "lathekin.png";
                break;
            case "martelletto":
                path = "martelletto.png";
                break;
            case "pennello per eglomise":
                path = "pennellopereglomise.png";
                break;
            case "pennello per pasta calda":
                path = "pennelloperpastacalda.png";
                break;
            case "pinza sgrossatrice":
                path = "pinzasgrossatrice.png";
                break;
            case "riga di sughero":
                path = "rigadisughero.png";
                break;
            case "taglierina cirolare":
                path = "taglierinacircolare.png";
                break;
            case "taglierina manuale":
                path = "taglierinamanuale.png";
                break;
            case "tampone diamantato":
                path = "tamponediamantato.png";
                break;
            case "tenaglia a rotelle":
                path = "tenagliaarotelle.png";
                break;

        }
        return new Image(getClass().getResourceAsStream(path));
    }
}
