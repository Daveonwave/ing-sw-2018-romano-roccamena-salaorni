package mvc.model.objects;

import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Window {
    //Finestra di un giocatore

    private Cell[][] cells;
    private int difficulty;

    //Costruttori
    public Window(Cell[][] cells, int difficulty) {
        this.cells = cells;
        this.difficulty = difficulty;
    }

    //Setter/Getter
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Cell[][] getCells() {
        return cells;
    }
    public int getDifficulty() {
        return difficulty;
    }

    //Verifica uguaglianze
    public boolean sameWindowStructure(Window window) {
        if (window == null)
            return false;

        for (int row=0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col=0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                if (cells[row][col].sameCellStructure(window.getCells()[row][col]))
                    return false;
            }
        }

        return true;
    }
    public boolean sameWindow(Window window) {
        for (int row=0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col=0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                if (cells[row][col].sameCell(window.getCells()[row][col]))
                    return false;
            }
        }

        return true;
    }

    //Ottiene una cella
    public synchronized Cell retrieveCell(Cell cell) throws RemoteException {
        Cell result = cells[cell.getRow()][cell.getColumn()];
        if (!result.sameCell(cell))
            throw new AppModelException("cella non valida");

        return result;
    }

    //Indica se la finestra è vuota
    public boolean isEmpty() {
        for (Cell[] cc : cells) {
            for (Cell c : cc) {
                if (c == null)
                        return false;

                if (c.getDie() != null)
                    return false;
            }
        }

        return true;
    }

    //Ottiene celle ortogonalmente adiacenti ad una data
    public Cell getUpCell(Cell cell) {
        if (cell.isNorthBorder())
            return null;
        else
            return cells[cell.getRow()-1][cell.getColumn()];
    }
    public Cell getDownCell(Cell cell) {
        if (cell.isSouthBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()];
    }
    public Cell getLeftCell(Cell cell) {
        if (cell.isNorthBorder())
            return null;
        else
            return cells[cell.getRow()][cell.getColumn()-1];
    }
    public Cell getRightCell(Cell cell) {
        if (cell.isNorthBorder())
            return null;
        else
            return cells[cell.getRow()][cell.getColumn()+1];
    }

    //Ottiene tutte le celle ortogonalmente adiacenti
    public Cell[] getAdjacentCells(Cell cell) {
        List<Cell> cells = new ArrayList<Cell>();

        Cell up = getUpCell(cell);
        Cell down = getDownCell(cell);
        Cell left = getLeftCell(cell);
        Cell right = getRightCell(cell);

        if (up != null)
            cells.add(up);
        if (down != null)
            cells.add(down);
        if (left != null)
            cells.add(left);
        if (right != null)
            cells.add(right);

        return (Cell[]) cells.toArray();
    }

    //Ottiene celle diagonalmente adiacenti ad una data
    public Cell getUpLeftCell(Cell cell) {
        if(cell.isNorthBorder() || cell.isWestBorder()){
            return null;
        }
        else
            return cells[cell.getRow()-1][cell.getColumn()-1];
    }
    public Cell getUpRightCell(Cell cell){
        if(cell.isNorthBorder() || cell.isEastBorder()){
            return null;
        }
        else
            return cells[cell.getRow()-1][cell.getColumn()+1];
    }
    public Cell getDownLeftCell(Cell cell) {
        if(cell.isSouthBorder() || cell.isWestBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()-1];
    }
    public Cell getDownRightCell(Cell cell){
        if(cell.isSouthBorder() || cell.isEastBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()+1];
    }

    //Ottiene tutte le celle diagonalmente adiacenti
    public List<Cell> getDiagonalsCells(Cell cell){
        List<Cell> cells = new ArrayList<Cell>();

        Cell upLeft = getUpLeftCell(cell);
        Cell upRight = getUpRightCell(cell);
        Cell downLeft = getDownLeftCell(cell);
        Cell downRight = getDownRightCell(cell);

        if(upLeft != null)
            cells.add(upLeft);
        if(upRight != null)
            cells.add(upRight);
        if(downLeft != null)
            cells.add(downLeft);
        if(downRight != null)
            cells.add(downRight);

        return cells;
    }

    //Verifica restrizioni di finestra
    public boolean noStartPlaceRestriction(Cell cell, Die die) {
        if (isEmpty()) {
            if (!cell.isNorthBorder()&&!cell.isSouthBorder()&&!cell.isWestBorder()&&!cell.isEastBorder())
                return false;
        }

        return true;
    }

    public boolean noAdjacentCellsRestriction(Cell cell, Die die) {
        Cell[] adjacentCells = getAdjacentCells(cell);
        for (Cell c : adjacentCells) {
            Die placedDie = c.getDie();

            if (die != null) {
                if (die.sameColor(placedDie) || die.sameShade(placedDie))
                    return false;
            }
        }

        return true;
    }

    public boolean noWindowRestriction(Cell cell, Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells) {
        if (ignoreStartPlace && ignoreAdjacentCells)
            return true;

        if (ignoreAdjacentCells)
            return noStartPlaceRestriction(cell, die);

        if (ignoreStartPlace)
            return noAdjacentCellsRestriction(cell, die);

        return noStartPlaceRestriction(cell, die) && noAdjacentCellsRestriction(cell, die);
    }
    public boolean noWindowRestriction(Cell cell, Die die) {
        return noWindowRestriction(cell, die, false, false);
    }

    //Ottiene celle piazzabili con un dado
    public List<Cell> getValidCells(Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells) {
        List<Cell> result = new ArrayList<Cell>();

        for (Cell[] cc : cells) {
            for (Cell c : cc) {
                if (noWindowRestriction(c, die, ignoreStartPlace, ignoreAdjacentCells))
                    if (c.getCellRestriction().canPlaceDie(die))
                        result.add(c);
            }
        }

        return result;
    }
    public List<Cell> getValidCells(Die die) {
        return getValidCells(die, false, false);
    }

    //Movimenti dadi fra celle
    public void placeDie(Cell cell, Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (!noWindowRestriction(cell, die, ignoreStartPlace, ignoreAdjacentCells))
            throw new MatchException("restrizioni di finestra non rispettate");

        //Esegue piazzamento
        cell.placeDie(die, ignoreColorRestriction, ignoreShadeRestriction);
    }
    public void placeDie(Cell cell, Die die) throws RemoteException {
        placeDie(cell, die, false, false, false, false);
    }

    public void moveDie(Cell origin, Cell destination, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (!noWindowRestriction(destination, origin.getDie(), ignoreStartPlace, ignoreAdjacentCells))
            throw new MatchException("restrizioni di finestra non rispettate");

        //Esegue movimento
        origin.moveDie(destination, ignoreColorRestriction, ignoreShadeRestriction);
    }
    public void moveDie(Cell origin, Cell destination) throws RemoteException {
        moveDie(origin, destination, false, false, false, false);
    }

    public void swapDice(Cell cell1, Cell cell2, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (!noWindowRestriction(cell1, cell2.getDie(), ignoreStartPlace, ignoreAdjacentCells) || !noWindowRestriction(cell2, cell1.getDie(), ignoreStartPlace, ignoreAdjacentCells))
            throw new MatchException("restrizioni di finestra non rispettate");

        //Esegue scambio
        cell1.swapDice(cell2, ignoreColorRestriction, ignoreShadeRestriction);
    }
    public void swapDice(Cell cell1, Cell cell2) throws RemoteException {
        swapDice(cell1, cell2, false, false, false, false);
    }

}
