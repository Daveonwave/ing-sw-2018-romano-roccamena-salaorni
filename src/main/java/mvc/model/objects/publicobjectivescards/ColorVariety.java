package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

import java.util.List;

public class ColorVariety extends PublicObjectiveCard {
    public ColorVariety() {
        super("Varietà di colore", "Set di dadi di ogni colore ovunque");
    }

    @Override
    public int getPoints(Window window) {
        int points;
        int lowerColorAmount = 20;
        List<Cell> coloredCells;

        //Ciclo su ogni gruppo di dadi con lo stesso colore
        for(DieColor dieColor : DieColor.values()){
            coloredCells = window.getSameColorCells(dieColor);

            if(lowerColorAmount > coloredCells.size())
                lowerColorAmount = coloredCells.size();
        }
        points = lowerColorAmount * 4;
        return points;
    }
}
