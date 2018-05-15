package mvc.model.objects;

public class ToolCardEffect {
    //Effetto di una carta strumento su un giocatore

    private Die choosenDie;
    private boolean skipTurn;
    private boolean replaceDie;
    private boolean ignoreAdjacentCellsRestriction;

    //Costruttori
    public ToolCardEffect(Die choosenDie, boolean skipTurn, boolean replaceDie, boolean ignoreAdjacentCellsRestriction) {
        this.choosenDie = choosenDie;
        this.skipTurn = skipTurn;
        this.replaceDie = replaceDie;
    }
    public ToolCardEffect() {
        this(null, false, false, false);
    }

    //Setter/Getter
    public void setChoosenDie(Die choosenDie) {
        this.choosenDie = choosenDie;
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

    public Die getChoosenDie() {
        return choosenDie;
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
