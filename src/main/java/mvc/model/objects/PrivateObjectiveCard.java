package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public abstract class PrivateObjectiveCard extends ColorCard {
    //Carta obiettivo privato

    //Costruttori
    public PrivateObjectiveCard(String name, String description, DieColor color) {
        super(name, description, color);
    }

    //Calcolo punteggio
    public abstract int getPoints(Window window);
}
