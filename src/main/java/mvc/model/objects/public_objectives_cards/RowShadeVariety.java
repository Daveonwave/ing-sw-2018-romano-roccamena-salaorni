package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.Cell;
import mvc.model.objects.GameConstants;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;

import java.util.List;

public class RowShadeVariety extends PublicObjectiveCard {
    public RowShadeVariety() {
        super("sfumature diverse - riga", "righe senza colori ripetuti");
    }

    @Override
    public int getPoints(Window window) {
        int points = 0;
        List<Cell> rowCells;

        //Ciclo ogni riga
        for(int indexRow = 0; indexRow < GameConstants.WINDOW_ROWS_COUNT; indexRow++){
            rowCells = window.getRowCells(indexRow);

            //Confronto ogni dado della riga con i successivi
            for(int first = 0; first < GameConstants.WINDOW_COLUMNS_COUNT; first++){
                for(int i = first + 1; i < GameConstants.WINDOW_COLUMNS_COUNT; i++){
                    if(rowCells.get(i).getDie().getShade() == rowCells.get(first).getDie().getShade())
                        break;
                }
                points = points + 5;
            }
        }
        return points;
    }
}
