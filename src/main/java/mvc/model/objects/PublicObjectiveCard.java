package mvc.model.objects;

import game.components.Card;
import mvc.model.objects.enums.PublicObjective;

public abstract class PublicObjectiveCard extends Card {
    //Carta obiettivo pubblico

    //Costruttori
    public PublicObjectiveCard(String name, String description) {
        super(name, description);
    }

    //Calcolo punteggio
    public abstract int getPoints(Window window);
}