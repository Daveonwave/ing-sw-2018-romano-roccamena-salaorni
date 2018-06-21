package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

public class DeepShades extends PublicObjectiveCard {
    public DeepShades() {
        super("sfumature scure", "set di 5 & 6 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        int points;
        int lowerSetAmount;

        List<Cell> shadeFiveCells = window.getSameShadeCells(5);
        List<Cell> shadeSixCells = window.getSameShadeCells(6);

        if(shadeFiveCells.size() <= shadeSixCells.size())
            lowerSetAmount = shadeFiveCells.size();
        else
            lowerSetAmount = shadeSixCells.size();

        points = lowerSetAmount * 2;
        return points;

    }
}
