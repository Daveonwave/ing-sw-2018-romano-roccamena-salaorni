package mvc.model.objects.privateobjectivescards;

import mvc.model.objects.GameConstants;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;

public class ShadesOfYellow extends PrivateObjectiveCard {
    public ShadesOfYellow() {
        super("sfumature gialle", "somma dei valori di tutti i dadi gialli.", GameConstants.YELLOW);
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        for (int i = 0; i < 4; i++){
            for(int j= 0; j < 5; j++){
                if(window.getCells()[i][j].getDie() != null) {
                    if (window.getCells()[i][j].getDie().getColor().equals(GameConstants.YELLOW)) {
                        points += window.getCells()[i][j].getDie().getShade();
                    }
                }
            }
        }
        return points;
    }
}
