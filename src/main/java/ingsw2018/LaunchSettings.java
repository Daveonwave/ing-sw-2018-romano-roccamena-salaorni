package ingsw2018;

public class LaunchSettings {
    // per settare la modalità di gioco all'avvio

    private boolean runWithGUI; // se false usa CLI
    private boolean runWithRMI; // se false usa socket

    //Costruttori
    public LaunchSettings(boolean runWithGUI, boolean runWithRMI) {
        this.runWithGUI = runWithGUI;
        this.runWithRMI = runWithRMI;
    }

    // Setter/getter
    public boolean isRunWithGUI() {
        return runWithGUI;
    }
    public boolean isRunWithRMI() {
        return runWithRMI;
    }

    public void setRunWithGUI(boolean runWithGUI) {
        this.runWithGUI = runWithGUI;
    }
    public void setRunWithRMI(boolean runWithRMI) {
        this.runWithRMI = runWithRMI;
    }
}
