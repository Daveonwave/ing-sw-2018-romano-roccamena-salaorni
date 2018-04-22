package mvc.model.objects;

import mvc.model.objects.Card;
import mvc.model.objects.enums.PrivateObjective;

public class PrivateObjectiveCard extends Card {
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
}
