package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class RowShadeVariety extends PublicObjectiveCard {
    public RowShadeVariety() {
        super("sfumature diverse - colonna", "colonne senza colori ripetuti");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
