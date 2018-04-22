package mvc.model.objects;

import mvc.model.objects.enums.WindowPattern;

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
}
