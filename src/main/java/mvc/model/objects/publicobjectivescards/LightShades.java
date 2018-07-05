package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;


/**
 * public objective card "light shades"
 */
public class LightShades extends PublicObjectiveCard {

    public LightShades() {
        super("sfumature chiare", "Set di 1 & 2 ovunque");
    }


    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
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
