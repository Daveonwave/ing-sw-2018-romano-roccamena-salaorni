package mvc.model.objects;

import mvc.model.objects.enums.PrivateObjective;

public abstract class PrivateObjectiveCard extends Card {
    //Carta obiettivo privato

    //Costruttori
    public PrivateObjectiveCard(String name, String description) {
        super(name, description);
    }

    //Calcolo punteggio
    public abstract int getPoints(Window window);
}
