package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

public class MediumShades extends PublicObjectiveCard {
    public MediumShades() {
        super("Sfumature medie", "Set di 3 & 4 ovunque");
    }

    @Override
    public int getPoints(Window window) {
        int points;
        int lowerSetAmount;

        List<Cell> shadeThreeCells = window.getSameShadeCells(3);
        List<Cell> shadeFourCells = window.getSameShadeCells(4);

        if(shadeThreeCells.size() <= shadeFourCells.size())
            lowerSetAmount = shadeThreeCells.size();
        else
            lowerSetAmount = shadeFourCells.size();

        points = lowerSetAmount * 2;
        return points;
    }
}
