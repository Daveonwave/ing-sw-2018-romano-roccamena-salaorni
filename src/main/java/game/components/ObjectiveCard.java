package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.Objective;

public class ObjectiveCard extends Card implements IdentifyStrategy<ObjectiveCard> {
    //Carta obiettivo

    private Objective objective;

    //Creatori
    public ObjectiveCard(String name, String description, Objective objective) {
        super(name, description);
        this.objective = objective;
    }

    //Setter/Getter
    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Objective getObjective() {
        return objective;
    }

    //Identificazione
    public boolean isSame(ObjectiveCard obj) {
        return getName().equals(obj.getName()) && getDescription().equals(obj.getDescription()) && objective == obj.getObjective();
    }
}
