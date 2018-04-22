package mvc.model.objects;

public class Player {
    //Giocatore di una partita

    private User user;
    private Window window;
    private ObjectiveCard objectiveCard;
    private int favorTokens;

    //Creatori
    public Player(User user, Window window, ObjectiveCard objectiveCard, int favorTokens) {
        this.window = window;
        this.objectiveCard = objectiveCard;
        this.favorTokens = favorTokens;
    }

    //Setter/Getter
    public void setUser(User user) {
        this.user = user;
    }
    public void setWindow(Window window) {
        this.window = window;
    }
    public void setObjectiveCard(ObjectiveCard objectiveCard) {
        this.objectiveCard = objectiveCard;
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
    public ObjectiveCard getObjectiveCard() {
        return objectiveCard;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
}
