package mvc.model.objects;

import mvc.model.objects.enums.DieColor;

public abstract class ToolCard extends Card{
    //Carta strumento

    private Match match;
    private int favorTokens;
    private DieColor dieColor;

    //Costruttori
    public ToolCard(String name, String description, Match match, int favorTokens, DieColor dieColor) {
        super(name, description);
        this.match = match;
        this.favorTokens = favorTokens;
        this.dieColor = dieColor;
    }

    //Setter/Getter
    public void setMatch(Match match) {
        this.match = match;
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setDieColor(DieColor dieColor) {
        this.dieColor = dieColor;
    }

    public Match getMatch() {
        return match;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
    public DieColor getDieColor() {
        return dieColor;
    }

    //Usa carta strumento
    public abstract void useToolCard(Match newMatch, Player player);
}
