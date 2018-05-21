package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

public class DeepShades extends PublicObjectiveCard {
    public DeepShades() {
        super("sfumature scure", "set di 5 & 6 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        return 0;
    }
}
