package game.match.args;

import game.components.*;

import java.io.Serializable;

public class MatchOutput implements Serializable {
    //Output di una partita

    private Match match;
    private Player player;
    private Window window;
    private Window[] windows;
    private ObjectiveCard objectiveCard;
    private PlayerPoints playerPoints;

    //Creatori
    public MatchOutput(Match match, Player player, Window window, Window[] windows, ObjectiveCard objectiveCard, PlayerPoints playerPoints) {
        this.match = match;
        this.player = player;
        this.window = window;
        this.windows = windows;
        this.objectiveCard = objectiveCard;
        this.playerPoints = playerPoints;
    }

    //Getter/Setter
    public void setMatch(Match match) {
        this.match = match;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setWindow(Window window) {
        this.window = window;
    }
    public void setWindows(Window[] windows) {
        this.windows = windows;
    }
    public void setObjectiveCard(ObjectiveCard objectiveCard) {
        this.objectiveCard = objectiveCard;
    }
    public void setPlayerPoints(PlayerPoints playerPoints) {
        this.playerPoints = playerPoints;
    }

    public Match getMatch() {
        return match;
    }
    public Player getPlayer() {
        return player;
    }
    public Window getWindow() {
        return window;
    }
    public Window[] getWindows() {
        return windows;
    }
    public ObjectiveCard getObjectiveCard() {
        return objectiveCard;
    }
    public PlayerPoints getPlayerPoints() {
        return playerPoints;
    }
}

