package mvc.view.gui;

import javafx.scene.image.ImageView;
import mvc.model.objects.Player;
import mvc.model.objects.Window;


public class OpponentView {

    private WindowView window;
    private Player player;

    public OpponentView(WindowView window, Player player) {
        this.window = window;
        this.player = player;
    }

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
