package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.GameConstants;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;


/**
 * public objective card "column color variety"
 */
public class ColumnColorVariety extends PublicObjectiveCard {
    public ColumnColorVariety() {
        super("colori diversi - colonna", "Colonne senza colori ripetuti");
    }


    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
    public int getPoints(Window window) {
        int points = 0;
        boolean varietyColumn;
        List<Cell> columnCells;

        //Ciclo ogni colonna
        for(int indexColumn = 0; indexColumn < GameConstants.WINDOW_COLUMNS_COUNT; indexColumn++){
            columnCells = window.getColumnCells(indexColumn);
            varietyColumn = true;
            //Confronto ogni dado della colonna con i successivi
            for(int first = 0; first < GameConstants.WINDOW_ROWS_COUNT; first++){
                for(int i = first + 1; i < GameConstants.WINDOW_ROWS_COUNT; i++){
                    if(columnCells.get(i).getDie() == null || columnCells.get(first).getDie() == null
                            || columnCells.get(i).getDie().getColor().equals(columnCells.get(first).getDie().getColor())){
                        varietyColumn = false;
                        break;
                    }
                }
            }
            if(varietyColumn){
                points += 5;
            }
        }
        return points;
    }

}
