package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

/**
 * public objective card "shade variety"
 */
public class ShadeVariety extends PublicObjectiveCard {

    public ShadeVariety() {
        super("Sfumature diverse", "Set di dadi di ogni valore ovunque");
    }


    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
    public int getPoints(Window window) {
        int points;
        int lowerShadeAmount = 20;
        List<Cell> shadeCells;

        //Ciclo su ogni gruppo di dadi con la stessa sfumatura
        for(int indexShade = 1; indexShade <= 6; indexShade++){
            shadeCells = window.getSameShadeCells(indexShade);

            if(shadeCells.size() < lowerShadeAmount){
                lowerShadeAmount = shadeCells.size();
            }
        }
        points = lowerShadeAmount * 5;
        return points;
    }
}
