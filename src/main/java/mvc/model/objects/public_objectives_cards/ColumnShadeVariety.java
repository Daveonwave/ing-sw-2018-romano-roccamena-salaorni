package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class ColumnShadeVariety extends PublicObjectiveCard {
    public ColumnShadeVariety() {
        super("sfumature diverse - colonna", "colonne senza sfumature ripetute");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
