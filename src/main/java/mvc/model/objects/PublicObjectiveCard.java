package mvc.model.objects;

import game.components.Card;
import mvc.model.objects.enums.PublicObjective;

public class PublicObjectiveCard extends Card {
    //Carta obiettivo pubblico

    private PublicObjective objective;

    //Creatori
    public PublicObjectiveCard(String name, String description, PublicObjective objective) {
        super(name, description);
        this.objective = objective;
    }

    //Getter/Setter
    public PublicObjective getObjective() {
        return objective;
    }

    public void setObjective(PublicObjective objective) {
        this.objective = objective;
    }
}