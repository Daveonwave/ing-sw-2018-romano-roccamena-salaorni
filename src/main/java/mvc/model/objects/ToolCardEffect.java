package mvc.model.objects;

import java.io.Serializable;

/**
 * Effect of tool cards
 */
public class ToolCardEffect implements Serializable {
    //Effetto di una carta strumento su un giocatore

    private Die chosenDie;
    private boolean skipTurn;
    private boolean replaceDie;
    private boolean ignoreIsolatedRestriction;

    /**
     * Constructor of a specific effect
     * @param chosenDie selected die
     * @param skipTurn flag for some effects
     * @param replaceDie flag for some effects
     * @param ignoreIsolatedRestriction flag for some effects
     */
    public ToolCardEffect(Die chosenDie, boolean skipTurn, boolean replaceDie, boolean ignoreIsolatedRestriction) {
        this.chosenDie = chosenDie;
        this.skipTurn = skipTurn;
        this.replaceDie = replaceDie;
        this.ignoreIsolatedRestriction = ignoreIsolatedRestriction;
    }
    /**
     * Generic Constructor
     */
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
    public void setIgnoreIsolatedRestriction(boolean ignoreIsolatedRestriction) {
        this.ignoreIsolatedRestriction = ignoreIsolatedRestriction;
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
    public boolean getIgnoreIsolatedRestriction() {
        return ignoreIsolatedRestriction;
    }
}
