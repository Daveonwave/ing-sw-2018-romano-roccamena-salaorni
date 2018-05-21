package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class ColorVariety extends PublicObjectiveCard {
    public ColorVariety() {
        super("varietà di colore", "set di dadi di ogni coore ovunque");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
