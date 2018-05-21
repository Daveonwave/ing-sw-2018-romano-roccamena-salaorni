package mvc.model.objects.private_objectives_cards;

import mvc.model.objects.GameConstants;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;

import java.awt.*;

public class ShadesOfPurple extends PrivateObjectiveCard {
    public ShadesOfPurple() {
        super("sfumature viola", "somma dei valori di utti i dadi viola. ", GameConstants.PURPLE);
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        for (int i = 0; i < 4; i++){
            for(int j= 0; j < 5; j++){
                if(window.getCells()[i][j].getDie().getColor().equals(this.getColor())){
                    points += window.getCells()[i][j].getDie().getShade();
                }
            }
        }
        return points;
    }
}
