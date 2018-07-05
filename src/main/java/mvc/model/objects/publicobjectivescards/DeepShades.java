package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;


/**
 * public objective card "deep shades"
 */
public class DeepShades extends PublicObjectiveCard {
    public DeepShades() {
        super("sfumature scure", "Set di 5 & 6 ovunque");
    }


    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
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
