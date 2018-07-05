package mvc.model.objects.publicobjectivescards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

import java.util.List;

/**
 * private objective card "color diagonals"
 */
public class ColorDiagonals extends PublicObjectiveCard {

    //Costruttori
    public ColorDiagonals() {
        super("diagonali colorate", "Numero di dadi" +
                " dello stesso colore diagonalmente adiacenti");
    }

    /**
     * calculate the points associated to this card of a window
     * @param window Window instance
     * @return points calculated
     */
    public int getPoints(Window window) {

        int points = 0;

        List<Cell> firstRow = window.getRowCells(0);
        List<Cell> firstColumn = window.getColumnCells(0);
        List<Cell> lastColumn = window.getColumnCells(4);

        for (Cell c : firstRow) {
            List<Cell> harmoniusDiagonal = window.getHarmoniousDiagonal(c);
            points += compareColorDiagonals(harmoniusDiagonal);

            List<Cell> disharmoniusDiagonal = window.getDisharmoniousDiagonal(c);
            points += compareColorDiagonals(disharmoniusDiagonal);

        }

        for(Cell c : firstColumn){
            if(firstColumn.indexOf(c) == 0){
                continue;
            }
            List<Cell> harmoniousDiagonal = window.getHarmoniousDiagonal(c);
            points += compareColorDiagonals(harmoniousDiagonal);
        }

        for(Cell c : lastColumn){
            if(lastColumn.indexOf(c) == 0){
                continue;
            }
            List<Cell> disharmoniousDiagonal = window.getDisharmoniousDiagonal(c);
            points += compareColorDiagonals(disharmoniousDiagonal);
        }

        return points;
    }

    //Controlla i colori uguali tra celle adiacenti
    public int compareColorDiagonals(List<Cell> diagonal){
        int points = 0;
        int temp = 0;
        DieColor color = null;

        for (Cell c : diagonal){
            if(c.getDie() != null){
                if(c.getDie().getColor().equals(color)){
                    points += temp+1;
                    temp = 0;
                }else{
                    color = c.getDie().getColor();
                    temp = 1;
                }
            }else {
                color = null;
            }
        }
        return points;
    }
}
