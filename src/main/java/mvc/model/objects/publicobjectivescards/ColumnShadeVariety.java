package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.GameConstants;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

public class ColumnShadeVariety extends PublicObjectiveCard {
    public ColumnShadeVariety() {
        super("sfumature diverse - colonna", "colonne senza sfumature ripetute");
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        List<Cell> columnCells;

        //Ciclo ogni colonna
        for(int indexColumn = 0; indexColumn < GameConstants.WINDOW_COLUMNS_COUNT; indexColumn++){
            columnCells = window.getColumnCells(indexColumn);

            //Confronto ogni dado della colonna con i successivi
            for(int first = 0; first < GameConstants.WINDOW_ROWS_COUNT; first++){
                for(int i = first + 1; i < GameConstants.WINDOW_ROWS_COUNT; i++){
                    if(columnCells.get(i).getDie().getShade() == columnCells.get(first).getDie().getShade())
                        break;
                }
                points = points + 4;
            }
        }
        return points;
    }
}
