package mvc.model.objects.privateobjectivescards;

import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

public class ShadesOfBlue extends PrivateObjectiveCard {
    public ShadesOfBlue() {
        super("sfumature blu","Somma dei valori su tutti i dadi blu.", DieColor.BLUE);
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        for (int i = 0; i < 4; i++){
            for(int j= 0; j < 5; j++){
                if(window.getCells()[i][j].getDie() != null) {
                    if (window.getCells()[i][j].getDie().getColor().equals(DieColor.BLUE)) {
                        points += window.getCells()[i][j].getDie().getShade();
                    }
                }
            }
        }
        return points;
    }
}
