package mvc.model.objects.privateobjectivescards;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

/**
 * private objective card "shades of blue"
 */
public class ShadesOfPurple extends PrivateObjectiveCard {
    public ShadesOfPurple() {
        super("sfumature viola", "Somma dei valori di utti i dadi viola. ", DieColor.PURPLE);
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
                if(window.getCells()[i][j].getDie() != null && window.getCells()[i][j].getDie().getColor().equals(DieColor.PURPLE)) {
                    points += window.getCells()[i][j].getDie().getShade();
                }
            }
        }
        return points;
    }
}
