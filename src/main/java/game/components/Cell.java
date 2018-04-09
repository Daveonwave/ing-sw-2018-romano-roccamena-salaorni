package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.Restriction;

public class Cell implements IdentifyStrategy<Cell> {
    //Cella di una finestra

    private Die die;
    private Restriction restriction;
    private int row;
    private int column;

    //Creatori
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

    //Identificazione
    public boolean isSame(Cell obj) {
        return die.isSame(obj.die) && restriction == obj.restriction;
    }
}
