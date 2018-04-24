package mvc.model.objects;

import java.util.List;

public class Player {
    //Giocatore di una partita

    private User user;
    private Window window;
    private List<Window> startWindows;
    private PrivateObjectiveCard privateObjectiveCard;
    private int favorTokens;

    //Costruttori
    public Player(User user, Window window, List<Window> startWindows, PrivateObjectiveCard objectiveCard, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;
        this.privateObjectiveCard = objectiveCard;
        this.favorTokens = favorTokens;
    }

    //Setter/Getter
    public void setUser(User user) {
        this.user = user;
    }
    public void setWindow(Window window) {
        this.window = window;
    }
    public void setStartWindows(List<Window> startWindows) {
        this.startWindows = startWindows;
    }
    public void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        this.privateObjectiveCard = privateObjectiveCard;
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public User getUser() {
        return user;
    }
    public Window getWindow() {
        return window;
    }
    public List<Window> getStartWindows() {
        return startWindows;
    }
    public PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
}
