package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class RowShadeVariety extends PublicObjectiveCard {
    public RowShadeVariety(String name, String description) {
        super(name, description);
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}