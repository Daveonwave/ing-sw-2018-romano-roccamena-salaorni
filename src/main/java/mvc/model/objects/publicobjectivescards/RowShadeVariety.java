package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.GameConstants;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;


/**
 * public objective card "row shade variety"
 */
public class RowShadeVariety extends PublicObjectiveCard {
    public RowShadeVariety() {
        super("sfumature diverse - riga", "Righe senza colori ripetuti");
    }


    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
    public int getPoints(Window window) {
        int points = 0;
        boolean varietyRow;
        List<Cell> rowCells;

        //Ciclo ogni riga
        for(int indexRow = 0; indexRow < GameConstants.WINDOW_ROWS_COUNT; indexRow++){
            rowCells = window.getRowCells(indexRow);
            varietyRow = true;
            //Confronto ogni dado della riga con i successivi
            for(int first = 0; first < GameConstants.WINDOW_COLUMNS_COUNT; first++){
                for(int i = first + 1; i < GameConstants.WINDOW_COLUMNS_COUNT; i++){
                    if(rowCells.get(i).getDie() == null || rowCells.get(first).getDie() == null
                            || rowCells.get(i).getDie().getShade() == rowCells.get(first).getDie().getShade()){
                        varietyRow = false;
                        break;
                    }
                }
            }
            if(varietyRow){
                points += 5;
            }
        }
        return points;
    }
}
