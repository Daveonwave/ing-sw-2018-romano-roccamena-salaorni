package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class RowColorVariety extends PublicObjectiveCard {
    public RowColorVariety() {
        super("sfumature diverse - riga", "righe senza sfumature ripetute");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
