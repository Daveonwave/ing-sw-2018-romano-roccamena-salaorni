package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class LightShades extends PublicObjectiveCard {
    public LightShades() {
        super("sfumature chiare", "set di 1 & 2 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
