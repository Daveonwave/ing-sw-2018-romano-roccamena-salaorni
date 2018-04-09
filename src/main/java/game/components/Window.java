package game.components;

import game.components.base.IdentifyStrategy;

public class Window implements IdentifyStrategy<Window> {
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

    //Identificazione
    public boolean isSame(Window obj) {
        //Stesso numero di righe
        if (cells.length != obj.cells.length) {
            return false;
        }

        //Per ogni riga
        for (int i=0; i<cells.length; i++) {
            //Stesso numero elementi
            if (cells[i].length != obj.cells[i].length) {
                return false;
            }

            //Stessi elementi
            for (int j=0; j<cells[i].length; j++) {
                if (!cells[i][j].isSame(obj.cells[i][j])) {
                    return false;
                }
            }
        }

        return difficulty == obj.difficulty;
    }
}
