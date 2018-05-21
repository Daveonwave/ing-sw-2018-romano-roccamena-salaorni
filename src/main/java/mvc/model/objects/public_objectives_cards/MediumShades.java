package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class MediumShades extends PublicObjectiveCard {
    public MediumShades() {
        super("sfumature medie", "set di 3 & 4 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
