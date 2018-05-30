package mvc.model.objects;

import java.io.Serializable;

public class PlayerPoints implements Serializable {
    //Punti di un giocatore

    private int privateObjectivePoints;
    private int publicObjectivePoints;
    private int favorTokensPoints;
    private int openSpacesLostPoints;

    //Costruttori
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
    public int getTotalPoints() {
        return privateObjectivePoints + publicObjectivePoints + favorTokensPoints + openSpacesLostPoints;
    }
}
