package mvc.model.objects.public_objectives_cards;

import mvc.model.objects.Cell;
import mvc.model.objects.PublicObjectiveCard;
import mvc.model.objects.Window;
import mvc.model.objects.enums.DieColor;

import java.util.List;

public class ColorDiagonals extends PublicObjectiveCard {

    //Costruttori
    public ColorDiagonals() {
        super("diagonali colorate", "numero di dadi" +
                " dello stesso colore diagonalmente adiacenti");
    }

    @Override
    public int getPoints(Window window) {

        int points = 0;

        List<Cell> firstRow = window.getRowCells(0);
        List<Cell> firstColumn = window.getColumnCells(0);
        List<Cell> lastColumn = window.getColumnCells(4);

        for (Cell c : firstRow) {
            List<Cell> harmoniusDiagonal = window.getHarmoniousDiagonal(c);
            points = compareColorDiagonals(harmoniusDiagonal);

            List<Cell> disharmoniusDiagonal = window.getDisharmoniousDiagonal(c);
            points += compareColorDiagonals(disharmoniusDiagonal);

        }

        for(Cell c : firstColumn){
            List<Cell> harmoniousDiagonal = window.getHarmoniousDiagonal(c);
            points += compareColorDiagonals(harmoniousDiagonal);
        }

        for(Cell c : lastColumn){
            List<Cell> disharmoniousDiagonal = window.getDisharmoniousDiagonal(c);
            points += compareColorDiagonals(disharmoniousDiagonal);
        }

        return points;
    }

    //Controlla i colori uguali tra celle adiacenti
    public int compareColorDiagonals(List<Cell> diagonal){
        int points = 0;

        //Confronta le prime due celle
        for(int i = 1; i < diagonal.size(); i++) {
            if(diagonal.get(i).getDie() != null && diagonal.get(i).getDie().getColor().equals(diagonal.get(i - 1).getDie().getColor()) && i == 1) {
                points = points + 2;
            }

            if (i + 1 == diagonal.size())
                break; //Esce all'ultima cella
            else {
                if(diagonal.get(i).getDie() != null && diagonal.get(i).getDie().getColor().equals(diagonal.get(i - 1).getDie().getColor())) {
                    if(diagonal.get(i).getDie().getColor().equals(diagonal.get(i + 1).getDie().getColor())){
                        points++;
                    }
                }
                else {
                    if(diagonal.get(i).getDie().getColor().equals(diagonal.get(i + 1).getDie().getColor())){
                        points = points + 2;
                    }
                }
            }
        }

        return points;
    }
}
