package mvc.model.objects;

import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Player of a game match
 */
public class Player implements Serializable {
    //Giocatore di una partita

    private User user;
    private Window window;
    private List<Window> startWindows;
    private List<PrivateObjectiveCard> privateObjectiveCards;
    private int favorTokens;
    private ToolCardEffect toolCardEffect;
    private boolean turnDiePlaced;
    private boolean turnToolCardUsed;
    private boolean active;

    //Costruttori
    //MultiPlayer -> un solo obiettivo privato
    /**
     * Create new multiplayer player
     * @param user User of the player
     * @param window Window of the player
     * @param startWindows Player's start windows
     * @param privateObjectiveCard Private objective cards of the player
     * @param favorTokens Player's favor tokens
     */
    public Player(User user, Window window, List<Window> startWindows, PrivateObjectiveCard privateObjectiveCard, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;

        this.privateObjectiveCards = new ArrayList<>();
        this.privateObjectiveCards.add(privateObjectiveCard);

        this.favorTokens = favorTokens;
        this.toolCardEffect = new ToolCardEffect();

        this.turnDiePlaced = false;
        this.turnToolCardUsed = false;

        this.active = true;
    }
    //SinglePlayer -> due obiettivi privati
    /**
     * Create new singleplayer player
     * @param user User of the player
     * @param window Window of the player
     * @param startWindows Player's start windows
     * @param privateObjectiveCards Private objective card of the player
     * @param favorTokens Player's favor tokens
     */
    public Player(User user, Window window, List<Window> startWindows, List<PrivateObjectiveCard> privateObjectiveCards, int favorTokens) {
        this.user = user;
        this.window = window;
        this.startWindows = startWindows;

        this.privateObjectiveCards = privateObjectiveCards;

        this.favorTokens = favorTokens;
        this.toolCardEffect = new ToolCardEffect();

        this.turnDiePlaced = false;
        this.turnToolCardUsed = false;
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
    public void setTurnToolCardUsed(boolean turnToolCardUsed) {
        this.turnToolCardUsed = turnToolCardUsed;
    }
    public void setActive(boolean active) {
        this.active = active;
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
    public boolean getTurnToolCardUsed() {
        return turnToolCardUsed;
    }
    public boolean isActive() {
        return active;
    }

    //Verifica uguaglianze
    /**
     * Asserts equality of two players
     * @param player Player instance
     * @return
     */
    public boolean samePlayer(Player player) {
        if (player == null)
            return false;

        User otherUser = player.getUser();

        if (otherUser == null)
            return false;

        return otherUser.sameUser(user);
    }

    //Ottiene finestra iniziale
    /**
     * Obtain start window instance given a window state
     * @param window Window instance
     * @return
     * @throws RemoteException MatchException if invalid window passed
     */
    public synchronized Window retrieveStartWindow(Window window) throws RemoteException {
        if (window==null)
            throw new MatchException("finestra non valida");

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
