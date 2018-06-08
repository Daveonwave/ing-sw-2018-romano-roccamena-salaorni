package gui.objects;

import mvc.model.objects.Player;

import java.io.Serializable;


public class PlayerView implements Serializable{

    private WindowView window;
    private Player player;

    //Costruttori
    public PlayerView(WindowView window, Player player) {
        this.window = window;
        this.player = player;
    }

    //Getter/Setter
    public WindowView getWindow() {
        return window;
    }
    public Player getPlayer() {
        return player;
    }

    public void setWindow(WindowView window) {
        this.window = window;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
