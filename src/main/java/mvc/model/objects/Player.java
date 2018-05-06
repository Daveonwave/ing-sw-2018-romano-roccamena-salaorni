package mvc.model.objects;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{
    //Giocatore di una partita

    private User user;
    private Window window;
    private List<Window> startWindows;
    private List<PrivateObjectiveCard> privateObjectiveCard;
    private int favorTokens;
    private PlayerPoints points;

    //Costruttori
    //MultiPlayer -> un solo obiettivo privato
    public Player(User user, Window window, List<Window> startWindows, PrivateObjectiveCard privateObjectiveCard, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;
        this.privateObjectiveCard.add(privateObjectiveCard);
        this.favorTokens = favorTokens;
    }
    //SinglePlayer -> due obiettivi privati
    public Player(User user, Window window, List<Window> startWindows, List<PrivateObjectiveCard> privateObjectiveCards, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;
        this.privateObjectiveCard = privateObjectiveCards;
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
        this.privateObjectiveCard.add(privateObjectiveCard);
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
    public List<PrivateObjectiveCard> getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
}
