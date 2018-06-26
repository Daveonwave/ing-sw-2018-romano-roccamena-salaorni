package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Cell of a game window
 */
public class Cell implements Serializable {
    //Cella di una finestra

    private Die die;
    private CellRestriction cellRestriction;
    private int row;
    private int column;

    //Costruttori
    /**
     * Create new cell
     * @param die Die placed inside the cell
     * @param cellRestriction Cell's restriction
     * @param row Row index of the window
     * @param column Column index of the row
     */
    public Cell(Die die, CellRestriction cellRestriction, int row, int column) {
        this.die = die;
        this.cellRestriction = cellRestriction;
        this.row = row;
        this.column = column;

    }
    /**
     * Create new cell from other cell
     * @param cell Cell instance
     */
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
    /**
     * Assert equality of two cell's dice
     * @param cell cell instance
     * @return
     */
    public boolean sameDie(Cell cell) {
        if (cell == null)
            return false;

        Die otherDie = cell.getDie();

        if (die == null && otherDie == null)
            return true;
        if (die == null ^ otherDie == null)
            return false;

        return otherDie.sameDie(die);
    }
    /**
     * Assert equality of restrictions of two cells
     * @param cell cell instance
     * @return
     */
    public boolean sameRestriction(Cell cell) {
        if (cell == null)
            return false;

        CellRestriction otherRestriction = cell.getCellRestriction();

        if (otherRestriction == null && cellRestriction == null)
            return true;
        if (otherRestriction == null ^ cellRestriction == null)
            return false;

        return otherRestriction.sameCellRestriction(cellRestriction);
    }
    /**
     * Assert equality of positions of two cells on their windows
     * @param cell cell instance
     * @return
     */
    public boolean samePosition(Cell cell) {
        if (cell == null)
            return false;

        return row == cell.getRow() && column == cell.getColumn();
    }
    /**
     * Assert equality of the structure of two cells (restrictions and positions) without testing placed dice
     * @param cell cell instance
     * @return
     */
    public boolean sameCellStructure(Cell cell) {
        return sameRestriction(cell) && samePosition(cell);
    }
    /**
     * Assert equality of placed dice in two cells
     * @param cell cell instance
     * @return
     */
    public boolean sameCell(Cell cell) {
        return sameCellStructure(cell) && sameDie(cell);
    }

    //Indicano se la cella è in posizioni di bordo dela finestra
    /**
     * Assert if the cell is located in windows's north border
     * @return
     */
    public boolean isNorthBorder() {
        return row==0;
    }
    /**
     * Assert if the cell is located in windows's south border
     * @return
     */
    public boolean isSouthBorder() {
        return row==GameConstants.WINDOW_ROWS_COUNT-1;
    }
    /**
     * Assert if the cell is located in windows's west border
     * @return
     */
    public boolean isWestBorder() {
        return column==0;
    }
    /**
     * Assert if the cell is located in windows's east border
     * @return
     */
    public boolean isEastBorder() {
        return column==GameConstants.WINDOW_COLUMNS_COUNT-1;
    }
    /**
     * Assert if the cell is located in windows's borders
     * @return
     */
    public boolean isInBorder() {
        return isNorthBorder() || isSouthBorder() || isWestBorder() || isEastBorder();
    }

    //Verifica restrizioni di cella
    /**
     * Assert if the cell has some cell restriction
     * @param die Die instance
     * @param ignoreColorRestriction True if ignoring color restriction needed, false otherwise
     * @param ignoreShadeRestriction True if ignoring shade restriction needed, false otherwise
     * @return
     */
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
    /**
     * Assert if the cell has some cell restriction, without ignoring any of them
     * @param die Die instance
     * @return
     */
    public boolean noCellRestriction(Die die) {
        return noCellRestriction(die, false, false);
    }

    //Movimenti dadi fra celle
    /**
     * Place a die inside the cell
     * @param die Die instance
     * @param ignoreColorRestriction True if ignoring color restriction needed, false otherwise
     * @param ignoreShadeRestriction True if ignoring shade restriction needed, false otherwise
     * @throws RemoteException MatchException if illegal die place
     */
    public void placeDie(Die die, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica che non ci sia un dado gia piazzato
        if (this.die!=null)
            throw new MatchException("la cella è gia occupata");
        //Verifica restrizioni di cella
        if (!noCellRestriction(die, ignoreColorRestriction, ignoreShadeRestriction))
            throw new MatchException("restrizione di cella non rispettata");

        //Esegue piazzamento
        this.die = die;
    }
    /**
     * Place a die inside the cell without ignoring any restriction
     * @param die Die instance
     * @throws RemoteException MatchException if illegal die place
     */
    public void placeDie(Die die) throws RemoteException {
        placeDie(die, false, false);
    }
    /**
     * Move die placed inside the cell into another cell
     * @param destination Destination cell
     * @param ignoreColorRestriction True if ignoring color restriction needed, false otherwise
     * @param ignoreShadeRestriction True if ignoring shade restriction needed, false otherwise
     * @throws RemoteException MatchException if illegal die move action
     */
    public void moveDie(Cell destination, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (die==null || destination.getDie()!=null)
            throw new MatchException("impossibile muovere dado");

        //Esegue movimento
        destination.placeDie(die, ignoreColorRestriction, ignoreShadeRestriction);
        this.die = null;
    }
    /**
     * Move die placed inside the cell into another cell without ignoring any restriction
     * @param destination Destination cell
     * @throws RemoteException MatchException if illegal die move action
     */
    public void moveDie(Cell destination) throws RemoteException {
        moveDie(destination, false, false);
    }
    /**
     * Swap dice placed whithin two cells
     * @param cell Cell instance
     * @param ignoreColorRestriction True if ignoring color restriction needed, false otherwise
     * @param ignoreShadeRestriction True if ignoring shade restriction needed, false otherwise
     * @throws RemoteException MatchException if illegal die swap action
     */
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
    /**
     * Swap dice placed within two cells without ignoring any restriction
     * @param cell Cell instance
     * @throws RemoteException MatchException if illegal die swap action
     */
    public void swapDice(Cell cell) throws RemoteException {
        swapDice(cell, false, false);
    }

}
