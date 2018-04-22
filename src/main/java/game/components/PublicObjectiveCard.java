package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.PublicObjective;

public class PublicObjectiveCard extends Card implements IdentifyStrategy<PublicObjectiveCard>{
    //Carta obiettivo pubblico
    private PublicObjective objective;

    //Creatore
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

    //Identificazione
    public boolean isSame(PublicObjectiveCard obj) {
        return getName().equals(obj.getName()) && getDescription().equals(obj.getDescription()) && objective == obj.getObjective();
    }

}