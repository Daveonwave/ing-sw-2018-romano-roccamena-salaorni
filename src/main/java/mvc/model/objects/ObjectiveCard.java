package mvc.model.objects;

public class ObjectiveCard extends Card {
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
}
