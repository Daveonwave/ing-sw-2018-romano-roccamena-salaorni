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

    public void setWindow(WindowView window) {
        this.window = window;
    }
}
