package mvc.model.objects;

import java.io.Serializable;

public class ToolCardEffect implements Serializable {
    //Effetto di una carta strumento su un giocatore

    private Die chosenDie;
    private boolean skipTurn;
    private boolean replaceDie;
    private boolean ignoreAdjacentCellsRestriction;

    //Costruttori
    public ToolCardEffect(Die chosenDie, boolean skipTurn, boolean replaceDie, boolean ignoreAdjacentCellsRestriction) {
        this.chosenDie = chosenDie;
        this.skipTurn = skipTurn;
        this.replaceDie = replaceDie;
        this.ignoreAdjacentCellsRestriction = ignoreAdjacentCellsRestriction;
    }
    public ToolCardEffect() {
        this(null, false, false, false);
    }

    //Setter/Getter
    public void setChosenDie(Die chosenDie) {
        this.chosenDie = chosenDie;
    }
    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }
    public void setReplaceDie(boolean replaceDie) {
        this.replaceDie = replaceDie;
    }
    public void setIgnoreAdjacentCellsRestriction(boolean ignoreAdjacentCellsRestriction) {
        this.ignoreAdjacentCellsRestriction = ignoreAdjacentCellsRestriction;
    }

    public Die getChosenDie() {
        return chosenDie;
    }
    public boolean getSkipTurn() {
        return skipTurn;
    }
    public boolean getReplaceDie() {
        return replaceDie;
    }
    public boolean getIgnoreAdjacentCellsRestriction() {
        return ignoreAdjacentCellsRestriction;
    }
}
