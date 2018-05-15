package mvc.model.objects;


import mvc.exceptions.MatchException;

import java.awt.*;
import java.rmi.RemoteException;

public class Cell {
    //Cella di una finestra

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
    public Cell(Cell cell) {
        this(cell.getDie(), cell.getCellRestriction(), cell.getRow(), cell.getColumn());
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

    //Verifica uguaglianze
    public boolean sameDie(Cell cell) {
        if (cell == null)
            return false;

        return cell.getDie().sameDie(die);
    }
    public boolean sameRestriction(Cell cell) {
        if (cell == null)
            return false;
        if (cell.getCellRestriction() == null)
            return false;

        return cell.getCellRestriction().sameCellRestriction(cellRestriction);
    }
    public boolean samePosition(Cell cell) {
        if (cell == null)
            return false;

        return row == cell.getRow() && column == cell.getColumn();
    }
    public boolean sameCellStructure(Cell cell) {
        return sameRestriction(cell) && samePosition(cell);
    }
    public boolean sameCell(Cell cell) {
        return sameCellStructure(cell) && sameDie(cell);
    }

    //Indicano se la cella è in posizioni di bordo dela finestra
    public boolean isNorthBorder() {
        return row==0;
    }
    public boolean isSouthBorder() {
        return row==GameConstants.WINDOW_ROWS_COUNT-1;
    }
    public boolean isWestBorder() {
        return column==0;
    }
    public boolean isEastBorder() {
        return column==GameConstants.WINDOW_COLUMNS_COUNT-1;
    }

    //Verifica restrizioni di cella
    public boolean noCellRestriction(Die die, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) {
        if (cellRestriction instanceof ColorRestriction) {
            if (ignoreColorRestriction)
                return true;
            else
                return cellRestriction.canPlaceDie(die);
        }
        else {
            if (cellRestriction instanceof ShadeRestriction) {
                if (ignoreShadeRestriction)
                    return true;
                else
                    return cellRestriction.canPlaceDie(die);
            } else
                return true;
        }
    }
    public boolean noCellRestriction(Die die) {
        return noCellRestriction(die, false, false);
    }

    //Movimenti dadi fra celle
    public void placeDie(Die die, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di cella
        if (!noCellRestriction(die, ignoreColorRestriction, ignoreShadeRestriction))
            throw new MatchException("restrizione di cella non rispettata");

        //Esegue piazzamento
        this.die = die;
    }
    public void placeDie(Die die) throws RemoteException {
        placeDie(die, false, false);
    }

    public void moveDie(Cell destination, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (destination.getDie() != null)
            throw new MatchException("impossibile muovere dado");

        //Esegue movimento
        destination.placeDie(die, ignoreColorRestriction, ignoreShadeRestriction);
        this.die = null;
    }
    public void moveDie(Cell destination) throws RemoteException {
        moveDie(destination, false, false);
    }

    public void swapDice(Cell cell, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Esegue scambio
        Die temp = die;

        placeDie(cell.getDie(), ignoreColorRestriction, ignoreShadeRestriction);
        try {
            cell.placeDie(temp, ignoreColorRestriction, ignoreShadeRestriction);
        } catch (MatchException e) {
            placeDie(temp, ignoreColorRestriction, ignoreShadeRestriction);
            throw e;
        }
    }
    public void swapDice(Cell cell) throws RemoteException {
        swapDice(cell, false, false);
    }

}
