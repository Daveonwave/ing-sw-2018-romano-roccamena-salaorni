package mvc.model.objects;

import mvc.model.objects.enums.WindowPattern;

import java.util.ArrayList;
import java.util.List;

public class Window {
    //Finestra di un giocatore

    private WindowPattern windowPattern;
    private Cell[][] cells;
    private int difficulty;

    //Costruttori
    public Window(WindowPattern windowPattern, Cell[][] cells, int difficulty) {
        this.windowPattern = windowPattern;
        this.cells = cells;
        this.difficulty = difficulty;
    }

    //Setter/Getter
    public void setWindowPattern(WindowPattern windowPattern) {
        this.windowPattern = windowPattern;
    }
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public WindowPattern getWindowPattern() {
        return windowPattern;
    }
    public Cell[][] getCells() {
        return cells;
    }
    public int getDifficulty() {
        return difficulty;
    }

    //Indica se la finestra possiede una cella
    public boolean containsCell(Cell cell) {
        for (Cell[] cc : cells) {
            for (Cell c : cc) {
                if (c.equals(cell))
                    return true;
            }
        }

        return false;
    }
    //Indica se la finestra è vuota
    public boolean isEmpty() {
        for (Cell[] cc : cells) {
            for (Cell c : cc) {
                if (c.getDie() != null)
                    return false;
            }
        }

        return false;
    }

    //Ottiene celle adiacenti ad una data
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

    //Ottiene tutte le celle adiacenti ad una data
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
}
