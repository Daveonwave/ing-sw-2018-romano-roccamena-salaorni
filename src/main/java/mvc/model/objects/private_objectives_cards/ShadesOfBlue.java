package mvc.model.objects.private_objectives_cards;

import mvc.model.objects.Cell;
import mvc.model.objects.PrivateObjectiveCard;
import mvc.model.objects.Window;

import java.awt.*;

public class ShadesOfBlue extends PrivateObjectiveCard {
    public ShadesOfBlue() {
        super("sfumature blu","somma dei valori su tutti i dadi blu.", Color.BLUE);
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
