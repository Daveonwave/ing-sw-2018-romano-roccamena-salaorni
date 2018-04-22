package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.PrivateObjective;

public class PrivateObjectiveCard extends Card implements IdentifyStrategy<PrivateObjectiveCard> {
    //Carta obiettivo privato

    private PrivateObjective objective;

    //Creatori
    public PrivateObjectiveCard(String name, String description, PrivateObjective objective) {
        super(name, description);
        this.objective = objective;
    }

    //Setter/Getter
    public void setObjective(PrivateObjective objective) {
        this.objective = objective;
    }

    public PrivateObjective getObjective() {
        return objective;
    }

    //Identificazione
    public boolean isSame(PrivateObjectiveCard obj) {
        return getName().equals(obj.getName()) && getDescription().equals(obj.getDescription()) && objective == obj.getObjective();
    }
}
