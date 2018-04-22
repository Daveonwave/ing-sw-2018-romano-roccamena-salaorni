package mvc.model.objects;

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
}
