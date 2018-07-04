package mvc.model.objects;

import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;
import mvc.model.objects.enums.DieColor;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game match window
 */
public class Window implements Serializable {
    //Finestra di un giocatore

    private Cell[][] cells;
    private int difficulty;
    private String name;

    //Costruttori
    /**
     * Create new window
     * @param cells Cells intances
     * @param difficulty Difficulty level
     * @param name Name of the window
     */
    public Window(Cell[][] cells, int difficulty, String name) {
        this.cells = cells;
        this.difficulty = difficulty;
        this.name = name;
    }

    //Setter/Getter
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Cell[][] getCells() {
        return cells;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public String getName() {
        return name;
    }

    //Verifica uguaglianze
    /**
     * Asserts equality of the structure two windows
     * @param window Window instance
     * @return
     */
    public boolean sameWindowStructure(Window window) {
        if (window == null)
            return false;

        Cell[][] otherCells = window.getCells();

        if (otherCells == null)
            return false;

        for (int row = 0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col = 0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                if (cells[row][col]==null^otherCells[row][col]==null)
                    return false;

                if (!cells[row][col].sameCellStructure(otherCells[row][col]))
                    return false;
            }
        }

        return true;
    }
    /**
     * Asserts equality of two windows
     * @param window Window instance
     * @return
     */
    public boolean sameWindow(Window window) {
        if (window == null)
            return false;

        Cell[][] otherCells = window.getCells();

        if (otherCells == null)
            return false;

        for (int row = 0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col = 0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                if (cells[row][col]==null^otherCells[row][col]==null)
                    return false;

                if (!cells[row][col].sameCell(otherCells[row][col]))
                    return false;
            }
        }

        return true;
    }

    //Ottiene una cella
    /**
     * Retrieve window's cell reference from given cell state
     * @param cell Cell instance
     * @return
     * @throws RemoteException AppModelException if invalid cell passed
     */
    public synchronized Cell retrieveCell(Cell cell) throws RemoteException {
        Cell result = cells[cell.getRow()][cell.getColumn()];
        if (!result.sameCell(cell))
            throw new AppModelException("cella non valida");

        return result;
    }

    //Indica se la finestra è vuota
    /**
     * Asserts the window has no dice placed
     * @return
     */
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

    //Verifica che dado o cella sia contenuto
    /**
     * Assert the window contains a given die
     * @param die
     * @return
     */
    public boolean containsDie(Die die) {
        for (int row = 0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col = 0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                Cell cell = cells[row][col];

                if (cell==null)
                    continue;

                if (die==null ^ cell.getDie()==null)
                    return false;
                if (die==null && cell.getDie()==null)
                    return true;

                if (cell.getDie().sameDie(die))
                    return true;
            }
        }

        return false;
    }
    /**
     * Asserts the window contains a given cell structure
     * @param cell Cell instance
     * @return
     */
    public boolean containsCellStructure(Cell cell) {
        for (int row = 0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col = 0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                Cell c = cells[row][col];

                if (c==null)
                    continue;

                if (c.sameCellStructure(cell))
                    return true;
            }
        }

        return false;
    }
    /**
     * Asserts the window contains a given cell
     * @param cell Cell instance
     * @return
     */
    public boolean containsCell(Cell cell) {
        for (int row = 0; row<GameConstants.WINDOW_ROWS_COUNT; row++) {
            for (int col = 0; col<GameConstants.WINDOW_COLUMNS_COUNT; col++) {
                Cell c = cells[row][col];

                if (c==null)
                    continue;

                if (c.sameCell(cell))
                    return true;
            }
        }

        return false;
    }

    //Ottiene una riga o colonna
    /**
     * Get list of cells of a given row of the window
     * @param rowIndex Row of the window
     * @return
     */
    public List<Cell> getRowCells(int rowIndex){
        List<Cell> result = new ArrayList<>();

        for(Cell[] cc : cells){
            for(Cell c : cc){
                if(c.getRow() == rowIndex)
                    result.add(c);
            }
        }
        return result;
    }
    /**
     * Get list of cells of a given column of the window
     * @param columnIndex Column of the window
     * @return
     */
    public List<Cell> getColumnCells(int columnIndex){
        List<Cell> result = new ArrayList<>();

        for(Cell[] cc : cells){
            for(Cell c : cc){
                if(c.getColumn() == columnIndex)
                    result.add(c);
            }
        }
        return result;
    }

    //Ottiene celle ortogonalmente adiacenti ad una data
    /**
     * Get upper cell of a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getUpCell(Cell cell) {
        if (cell.isNorthBorder())
            return null;
        else
            return cells[cell.getRow()-1][cell.getColumn()];
    }
    /**
     * Get down cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getDownCell(Cell cell) {
        if (cell.isSouthBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()];
    }
    /**
     * Get left cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getLeftCell(Cell cell) {
        if (cell.isWestBorder())
            return null;
        else
            return cells[cell.getRow()][cell.getColumn()-1];
    }
    /**
     * Get right cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getRightCell(Cell cell) {
        if (cell.isEastBorder())
            return null;
        else
            return cells[cell.getRow()][cell.getColumn()+1];
    }

    //Ottiene tutte le celle ortogonalmente adiacenti
    /**
     * Get orthogonal adjacent cells for a given cell
     * @param cell Cell instance
     * @return
     */
    public List<Cell> getOrthogonalCells(Cell cell) {
        List<Cell> cellList = new ArrayList<>();

        Cell up = getUpCell(cell);
        Cell down = getDownCell(cell);
        Cell left = getLeftCell(cell);
        Cell right = getRightCell(cell);

        if (up != null)
            cellList.add(up);
        if (down != null)
            cellList.add(down);
        if (left != null)
            cellList.add(left);
        if (right != null)
            cellList.add(right);

        return cellList;
    }

    //Ottiene celle diagonalmente adiacenti ad una data
    /**
     * Get upper left cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getUpLeftCell(Cell cell) {
        if(cell.isNorthBorder() || cell.isWestBorder()){
            return null;
        }
        else
            return cells[cell.getRow()-1][cell.getColumn()-1];
    }
    /**
     * Get upper right cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getUpRightCell(Cell cell){
        if(cell.isNorthBorder() || cell.isEastBorder()){
            return null;
        }
        else
            return cells[cell.getRow()-1][cell.getColumn()+1];
    }
    /**
     * Get down left cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getDownLeftCell(Cell cell) {
        if(cell.isSouthBorder() || cell.isWestBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()-1];
    }
    /**
     * Get down left cell for a given cell
     * @param cell Cell instance
     * @return
     */
    public Cell getDownRightCell(Cell cell){
        if(cell.isSouthBorder() || cell.isEastBorder())
            return null;
        else
            return cells[cell.getRow()+1][cell.getColumn()+1];
    }

    //Ottiene tutte le celle diagonalmente adiacenti
    /**
     * Get diagonal adjacent cells for a given cell
     * @param cell Cell instance
     * @return
     */
    public List<Cell> getDiagonalsCells(Cell cell){
        List<Cell> cellList = new ArrayList<>();

        Cell upLeft = getUpLeftCell(cell);
        Cell upRight = getUpRightCell(cell);
        Cell downLeft = getDownLeftCell(cell);
        Cell downRight = getDownRightCell(cell);

        if(upLeft != null)
            cellList.add(upLeft);
        if(upRight != null)
            cellList.add(upRight);
        if(downLeft != null)
            cellList.add(downLeft);
        if(downRight != null)
            cellList.add(downRight);

        return cellList;
    }

    //Ottieni diagonali armoniche e disarmoniche
    /**
     * Get harmonious diagonal for a given cell
     * @param cell Cell instance
     * @return
     */
    public List<Cell> getHarmoniousDiagonal(Cell cell){
        List<Cell> result = new ArrayList<>();

        result.add(cell);

        for(int i = 0; i < 3; i++) {
            if (!cell.isSouthBorder() && !cell.isEastBorder()) {
                cell = getDownRightCell(cell);
                result.add(cell);
            }
            else
                break;
        }
        return result;
    }
    /**
     * Get disharmoniuos diagonal for a given cell
     * @param cell Cell instance
     * @return
     */
    public List<Cell> getDisharmoniousDiagonal(Cell cell){
        List<Cell> result = new ArrayList<>();

        result.add(cell);

        for(int i = 0; i < 3; i++) {
            if (!cell.isSouthBorder() && !cell.isWestBorder()) {
                cell = getDownLeftCell(cell);
                result.add(cell);
            }
            else
                break;
        }
        return result;
    }

    //Ottiene celle per caratteristica
    /**
     * Obtain cells with given color
     * @param color Color value
     * @return
     */
    public List<Cell> getSameColorCells(DieColor color){
        List<Cell> result = new ArrayList<>();

        for(Cell[] cc : cells){
            for(Cell c : cc){
                if(c.getDie() != null && color.equals(c.getDie().getColor())) {
                    result.add(c);
                }
            }
        }
        return result;
    }
    /**
     * Obtain cells with given shade
     * @param shade Shade value
     * @return
     */
    public List<Cell> getSameShadeCells(int shade){
        List<Cell> result = new ArrayList<>();

        for(Cell[] cc : cells){
            for(Cell c : cc){
                if(c.getDie() != null && c.getDie().getShade() == shade) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    //Verifica restrizioni di finestra
    /**
     * Asserts no start place restriction are present for a given placement
     * @param cell Cell instance
     * @param die Die instance
     * @return
     */
    public boolean noStartPlaceRestriction(Cell cell, Die die) {
        return cell.isInBorder();
    }
    /**
     * Asserts no orthogonal adjacent restriction are present for a given placement
     * @param cell Cell instance
     * @param die Die instance
     * @return
     */
    public boolean noAdjacentCellsRestriction(Cell cell, Die die) {
        List<Cell> adjacentCells = getOrthogonalCells(cell);

        for (Cell c : adjacentCells) {
            Die placedDie = c.getDie();

            if (die != null && (die.sameColor(placedDie) || die.sameShade(placedDie))) {
                return false;
            }
        }

        return true;
    }
    /**
     * Asserts no isolated cell restriction are present for a given placement
     * @param cell Cell instance
     * @param die Die instance
     * @return
     */
    public boolean noIsolatedRestriction(Cell cell, Die die) {
        List<Cell> adjacentCells = getOrthogonalCells(cell);
        adjacentCells.addAll(getDiagonalsCells(cell));

        for (Cell c : adjacentCells) {
            if (c.getDie()!=null)
                return true;
        }

        return false;
    }

    /**
     * Asserts no window restriction are present for a given placement
     * @param cell Cell instance
     * @param die Die instance
     * @return
     */
    public boolean noWindowRestriction(Cell cell, Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells) {
        if (ignoreStartPlace && ignoreAdjacentCells)
            return true;

        if (ignoreAdjacentCells)
            if (isEmpty())
                return noStartPlaceRestriction(cell, die);
            else
                return true;

        if (ignoreStartPlace)
            return noAdjacentCellsRestriction(cell, die);

        if (isEmpty())
            return noStartPlaceRestriction(cell, die);
        else
            return noAdjacentCellsRestriction(cell, die) && noIsolatedRestriction(cell, die);
    }
    /**
     * Asserts no window restriction are present for a given placement
     * @param cell Cell instance
     * @param die Die instance
     * @return
     */
    public boolean noWindowRestriction(Cell cell, Die die) {
        return noWindowRestriction(cell, die, false, false);
    }

    //Ottiene celle piazzabili con un dado
    /**
     * Computes valid cells for a given die
     * @param die Die instance
     * @param ignoreStartPlace If needed to ignore start place restriction
     * @param ignoreAdjacentCells If needed to ignore orthogonal adjacent restriction
     * @return
     */
    public List<Cell> getValidCells(Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells) {
        List<Cell> result = new ArrayList<>();

        for (Cell[] cc : cells) {
            for (Cell c : cc) {
                if (noWindowRestriction(c, die, ignoreStartPlace, ignoreAdjacentCells) && c.getCellRestriction().canPlaceDie(die))
                    result.add(c);
            }
        }

        return result;
    }
    /**
     * Computes valid cells for a given die with all restrictions
     * @param die Die instance
     * @return
     */
    public List<Cell> getValidCells(Die die) {
        return getValidCells(die, false, false);
    }

    //Movimenti dadi fra celle
    /**
     * Place a die inside a cell
     * @param cell Cell instance
     * @param die Die instance
     * @param ignoreStartPlace If needed to ignore start place restriction
     * @param ignoreAdjacentCells If needed to ignore orthogonal adjacent restriction
     * @param ignoreColorRestriction If needed to ignore color restriction
     * @param ignoreShadeRestriction If needed to ignore shade restriction
     * @throws RemoteException MatchException if invalid action requested
     */
    public void placeDie(Cell cell, Die die, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (!noWindowRestriction(cell, die, ignoreStartPlace, ignoreAdjacentCells))
            throw new MatchException("restrizioni di finestra non rispettate");

        //Esegue piazzamento
        cell.placeDie(die, ignoreColorRestriction, ignoreShadeRestriction);
    }
    /**
     * Place a die inside a cell with all restrictions
     * @param cell Cell instance
     * @param die Die instance
     * @throws RemoteException MatchException if invalid action requested
     */
    public void placeDie(Cell cell, Die die) throws RemoteException {
        placeDie(cell, die, false, false, false, false);
    }

    /**
     * Move die from a cell to another
     * @param origin Origin cell
     * @param destination Destination cell
     * @param ignoreStartPlace If needed to ignore start place restriction
     * @param ignoreAdjacentCells If needed to ignore orthogonal adjacent restriction
     * @param ignoreColorRestriction If needed to ignore color restriction
     * @param ignoreShadeRestriction If needed to ignore shade restriction
     * @throws RemoteException MatchException if invalid action requested
     */
    public void moveDie(Cell origin, Cell destination, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        Die tempNode = origin.getDie();

        origin.setDie(null);

        if (!noWindowRestriction(destination, tempNode, ignoreStartPlace, ignoreAdjacentCells)) {
            origin.setDie(tempNode);

            throw new MatchException("restrizioni di finestra non rispettate");
        }
        origin.setDie(tempNode);

        //Esegue movimento
        origin.moveDie(destination, ignoreColorRestriction, ignoreShadeRestriction);
    }
    /**
     * Move die from a cell to another with all restrictions
     * @param origin Origin cell
     * @param destination Destination cell
     * @throws RemoteException MatchException if invalid action requested
     */
    public void moveDie(Cell origin, Cell destination) throws RemoteException {
        moveDie(origin, destination, false, false, false, false);
    }

    /**
     * Swap dice within two cells
     * @param cell1 Cell instance
     * @param cell2 Cell instance
     * @param ignoreStartPlace If needed to ignore start place restriction
     * @param ignoreAdjacentCells If needed to ignore orthogonal adjacent restriction
     * @param ignoreColorRestriction If needed to ignore color restriction
     * @param ignoreShadeRestriction If needed to ignore shade restriction
     * @throws RemoteException MatchException if invalid action requested
     */
    public void swapDice(Cell cell1, Cell cell2, boolean ignoreStartPlace, boolean ignoreAdjacentCells, boolean ignoreColorRestriction, boolean ignoreShadeRestriction) throws RemoteException {
        //Verifica restrizioni di finestra
        if (!noWindowRestriction(cell1, cell2.getDie(), ignoreStartPlace, ignoreAdjacentCells) || !noWindowRestriction(cell2, cell1.getDie(), ignoreStartPlace, ignoreAdjacentCells))
            throw new MatchException("restrizioni di finestra non rispettate");

        //Esegue scambio
        cell1.swapDice(cell2, ignoreColorRestriction, ignoreShadeRestriction);
    }
    /**
     * Swap dice within two cells with all restrictions
     * @param cell1 Cell instance
     * @param cell2 Cell intance
     * @throws RemoteException MatchException if invalid action requested
     */
    public void swapDice(Cell cell1, Cell cell2) throws RemoteException {
        swapDice(cell1, cell2, false, false, false, false);
    }

}
