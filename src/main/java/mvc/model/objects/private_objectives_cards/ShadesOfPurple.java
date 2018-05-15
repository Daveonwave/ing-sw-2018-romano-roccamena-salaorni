package mvc.model.objects.private_objectives_cards;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;

public class ShadesOfPurple extends PrivateObjectiveCard {
    public ShadesOfPurple(String name, String description) {
        super(name, description);
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
