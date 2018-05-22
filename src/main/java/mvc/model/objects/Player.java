package mvc.model.objects;

import mvc.exceptions.AppModelException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable{
    //Giocatore di una partita

    private User user;
    private Window window;
    private List<Window> startWindows;
    private List<PrivateObjectiveCard> privateObjectiveCards;
    private int favorTokens;
    private ToolCardEffect toolCardEffect;
    private boolean turnDiePlaced;

    //Costruttori
    //MultiPlayer -> un solo obiettivo privato
    public Player(User user, Window window, List<Window> startWindows, PrivateObjectiveCard privateObjectiveCard, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;

        this.privateObjectiveCards = new ArrayList<PrivateObjectiveCard>();
        this.privateObjectiveCards.add(privateObjectiveCard);

        this.favorTokens = favorTokens;
        this.toolCardEffect = new ToolCardEffect();
        this.turnDiePlaced = false;
    }
    //SinglePlayer -> due obiettivi privati
    public Player(User user, Window window, List<Window> startWindows, List<PrivateObjectiveCard> privateObjectiveCards, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;

        this.privateObjectiveCards = privateObjectiveCards;

        this.favorTokens = favorTokens;
        this.toolCardEffect = new ToolCardEffect();
        this.turnDiePlaced = false;
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
    public void setPrivateObjectiveCards(PrivateObjectiveCard privateObjectiveCards) {
        this.privateObjectiveCards.add(privateObjectiveCards);
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setToolCardEffect(ToolCardEffect toolCardEffect) {
        this.toolCardEffect = toolCardEffect;
    }
    public void setTurnDiePlaced(boolean turnDiePlaced) {
        this.turnDiePlaced = turnDiePlaced;
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
    public List<PrivateObjectiveCard> getPrivateObjectiveCards() {
        return privateObjectiveCards;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
    public ToolCardEffect getToolCardEffect() {
        return toolCardEffect;
    }
    public boolean getTurnDiePlaced() {
        return turnDiePlaced;
    }

    //Verifica uguaglianze
    public boolean samePlayer(Player player) {
        if (player == null)
            return false;
        if (player.getUser() == null)
            return false;

        return player.getUser().sameUser(user);
    }

    //Ottiene finestra iniziale
    public synchronized Window retrieveStartWindow(Window window) throws RemoteException {
        Window result = null;
        for (Window w : startWindows) {
            if (w.sameWindow(window)) {
                result = w;
                break;
            }
        }
        if (result == null)
            throw new AppModelException("finestra non valida");

        return result;
    }
}
