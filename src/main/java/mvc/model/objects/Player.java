package mvc.model.objects;

public class Player {
    //Giocatore di una partita

    private User user;
    private Window window;
    private PrivateObjectiveCard privateObjectiveCard;
    private int favorTokens;

    //Creatori
    public Player(User user, Window window, PrivateObjectiveCard objectiveCard, int favorTokens) {
        this.window = window;
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
    public PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
}
