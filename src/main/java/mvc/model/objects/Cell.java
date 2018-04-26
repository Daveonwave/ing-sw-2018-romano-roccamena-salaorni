package mvc.model.objects;

import mvc.model.objects.enums.Restriction;

import java.rmi.RemoteException;

public class Cell {
    //Cella di una finestra

    public final int MAX_ROW = 4;
    public final int MAX_COLUMN = 5;

    private Die die;
    private Restriction restriction;
    private int row;
    private int column;

    //Costruttori
    public Cell(Die die, Restriction restriction, int row, int column) {
        this.die = die;
        this.restriction = restriction;
        this.row = row;
        this.column = column;
    }

    //Setter/Getter
    public Die getDie() {
        return die;
    }
    public Restriction getRestriction() {
        return restriction;
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
    public void setRestriction(Restriction restriction) {
        this.restriction = restriction;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    //Indicano se la cella è in posizioni di bordo o angolo della finestra
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

    //TODO: implementazione, Restriction non incapsula i valori delle restrizioni
    //Piazza un dado nella cella
    public void placeDie(Die die) throws RemoteException {
        throw new MatchException("not implemented");
    }

}
