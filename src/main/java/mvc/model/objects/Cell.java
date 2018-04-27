package mvc.model.objects;

import java.rmi.RemoteException;

public class Cell {
    //Cella di una finestra

    public final int MAX_ROW = 4;
    public final int MAX_COLUMN = 5;

    private Die die;
    private CellRestriction cellRestriction;
    private int row;
    private int column;

    //Costruttori
    public Cell(Die die, CellRestriction cellRestriction, int row, int column) {
        this.die = die;
        this.cellRestriction = cellRestriction;
        this.row = row;
        this.column = column;
    }

    //Setter/Getter
    public Die getDie() {
        return die;
    }
    public CellRestriction getCellRestriction() {
        return cellRestriction;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public void setDie(Die die) {
        this.die = die;
    }
    public void setCellRestriction(CellRestriction cellRestriction) {
        this.cellRestriction = cellRestriction;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    //Indicano se la cella è in posizioni di bordo dela finestra
    public boolean isNorthBorder() {
        return row==0;
    }
    public boolean isSouthBorder() {
        return row==MAX_ROW-1;
    }
    public boolean isWestBorder() {
        return column==0;
    }
    public boolean isEastBorder() {
        return column==MAX_COLUMN-1;
    }

    //Piazza un dado nella cella
    public void placeDie(Die die) throws RemoteException {
        if (!cellRestriction.canPlaceDie(die))
            throw new MatchException("selected die must respect cell restriction");

        this.die = die;
    }

}
