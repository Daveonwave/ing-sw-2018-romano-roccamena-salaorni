package game;

public class LaunchSettings {
    // per settare la modalità di gioco all'avvio

    private boolean runWithGUI; // se false usa CLI
    private boolean runWithRMI; // se false usa socket
    private int numberOfPlayers;

    // Setter/getter
    public boolean isRunWithGUI() {
        return runWithGUI;
    }
    public boolean isRunWithRMI() {
        return runWithRMI;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setRunWithGUI(boolean runWithGUI) {
        this.runWithGUI = runWithGUI;
    }
    public void setRunWithRMI(boolean runWithRMI) {
        this.runWithRMI = runWithRMI;
    }
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
