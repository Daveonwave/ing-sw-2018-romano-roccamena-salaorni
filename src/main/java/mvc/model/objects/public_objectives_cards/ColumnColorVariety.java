package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class ColumnColorVariety extends PublicObjectiveCard {
    public ColumnColorVariety() {
        super("colori diversi - colonna", "colonne senza colori ripetuti");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
