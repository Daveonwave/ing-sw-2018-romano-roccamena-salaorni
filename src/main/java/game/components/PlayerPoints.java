package game.components;

import game.components.base.IdentifyStrategy;

public class PlayerPoints implements IdentifyStrategy<PlayerPoints> {
    //Punti di un giocatore

    private int privateObjectivePoints;
    private int publicObjectivePoints;
    private int favorTokensPoints;
    private int openSpacesLostPoints;

    //Creatori
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

    //Identificazione
    public boolean isSame(PlayerPoints obj) {
        return privateObjectivePoints == obj.getPrivateObjectivePoints() &&  publicObjectivePoints == obj.getPublicObjectivePoints() && favorTokensPoints == obj.getFavorTokensPoints() && openSpacesLostPoints == obj.getOpenSpacesLostPoints();
    }
}