package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

public class LightShades extends PublicObjectiveCard {
    public LightShades() {
        super("sfumature chiare", "set di 1 & 2 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        int points;
        int lowerSetAmount;

        List<Cell> shadeOneCells = window.getSameShadeCells(1);
        List<Cell> shadeTwoCells = window.getSameShadeCells(2);

        if(shadeOneCells.size() <= shadeTwoCells.size())
            lowerSetAmount = shadeOneCells.size();
        else
            lowerSetAmount = shadeTwoCells.size();

        points = lowerSetAmount * 2;
        return points;
    }
}
