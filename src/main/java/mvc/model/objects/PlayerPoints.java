package mvc.model.objects;

import java.io.Serializable;

/**
 * Match points of a player
 */
public class PlayerPoints implements Serializable {
    //Punti di un giocatore

    private int privateObjectivePoints;
    private int publicObjectivePoints;
    private int favorTokensPoints;
    private int openSpacesLostPoints;

    //Costruttori
    /**
     * Create new player's points
     * @param privateObjectivePoints Private objective points count
     * @param publicObjectivePoints Public objective points count
     * @param favorTokensPoints Favor tokens points count
     * @param openSpacesLostPoints Open spaces lost points count
     */
    public PlayerPoints(int privateObjectivePoints, int publicObjectivePoints, int favorTokensPoints, int openSpacesLostPoints) {
        this.privateObjectivePoints = privateObjectivePoints;
        this.publicObjectivePoints = publicObjectivePoints;
        this.favorTokensPoints = favorTokensPoints;
        this.openSpacesLostPoints = openSpacesLostPoints;
    }

    //Setter/Getter
    public void setPrivateObjectivePoints(int privateObjectivePoints) {
        this.privateObjectivePoints = privateObjectivePoints;
    }
    public void setPublicObjectivePoints(int publicObjectivePoints) {
        this.publicObjectivePoints = publicObjectivePoints;
    }
    public void setFavorTokensPoints(int favorTokensPoints) {
        this.favorTokensPoints = favorTokensPoints;
    }
    public void setOpenSpacesLostPoints(int openSpacesLostPoints) {
        this.openSpacesLostPoints = openSpacesLostPoints;
    }

    public int getPrivateObjectivePoints() {
        return privateObjectivePoints;
    }
    public int getPublicObjectivePoints() {
        return publicObjectivePoints;
    }
    public int getFavorTokensPoints() {
        return favorTokensPoints;
    }
    public int getOpenSpacesLostPoints() {
        return openSpacesLostPoints;
    }

    //Ottiene punti totali
    /**
     * Calculate total player points
     * @return
     */
    public int getTotalPoints() {
        return privateObjectivePoints + publicObjectivePoints + favorTokensPoints + openSpacesLostPoints;
    }
}
