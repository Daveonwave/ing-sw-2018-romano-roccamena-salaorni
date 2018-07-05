package mvc.model.objects.privateobjectivescards;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

/**
 * Private objective card "shades of blue"
 */
public class ShadesOfBlue extends PrivateObjectiveCard {
    public ShadesOfBlue() {
        super("sfumature blu","Somma dei valori su tutti i dadi blu.", DieColor.BLUE);
    }

    /**
     * calculates the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
    public int getPoints(Window window) {
        int points = 0;
        for (int i = 0; i < 4; i++){
            for(int j= 0; j < 5; j++){
                if(window.getCells()[i][j].getDie() != null && window.getCells()[i][j].getDie().getColor().equals(DieColor.BLUE)) {
                    points += window.getCells()[i][j].getDie().getShade();
                }
            }
        }
        return points;
    }
}
