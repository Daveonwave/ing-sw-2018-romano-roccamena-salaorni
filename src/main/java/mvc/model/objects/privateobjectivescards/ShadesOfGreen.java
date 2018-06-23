package mvc.model.objects.privateobjectivescards;

import mvc.model.objects.GameConstants;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;

public class ShadesOfGreen extends PrivateObjectiveCard {
    public ShadesOfGreen() {
        super("sfumature verdi", "somma dei valori di tutti i dadi verdi.", GameConstants.GREEN);
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        for (int i = 0; i < 4; i++){
            for(int j= 0; j < 5; j++){
                if(window.getCells()[i][j].getDie() != null) {
                    if (window.getCells()[i][j].getDie().getColor().equals(GameConstants.GREEN)) {
                        points += window.getCells()[i][j].getDie().getShade();
                    }
                }

            }
        }
        return points;
    }
}
