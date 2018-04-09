package game.components;

import game.components.base.IdentifyStrategy;

public class Player implements IdentifyStrategy<Player> {
    //Giocatore di una partita

    private String name;
    private Window window;
    private ObjectiveCard objectiveCard;
    private int favorTokens;

    //Creatori
    public Player(String name, Window window, ObjectiveCard objectiveCard, int favorTokens) {
        this.name = name;
        this.window = window;
        this.objectiveCard = objectiveCard;
        this.favorTokens = favorTokens;
    }

    //Setter/Getter
    public void setName(String name) {
        this.name = name;
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

    public String getName() {
        return name;
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

    //Identificazione
    public boolean isSame(Player obj) {
        return name.equals(obj.name) && window.isSame(obj.getWindow()) && objectiveCard.isSame(obj.getObjectiveCard()) && favorTokens == obj.getFavorTokens();
    }
}
