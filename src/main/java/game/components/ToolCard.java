package game.components;

import game.components.base.IdentifyStrategy;
import game.components.base.Tool;

public class ToolCard extends Card implements IdentifyStrategy<ToolCard> {
    //Carta strumento

    private Tool tool;
    private int favorTokens;

    //Costruttore
    public ToolCard(String name, String description, Tool tool, int favorTokens) {
        super(name, description);
        this.tool = tool;
        this.favorTokens = favorTokens;
    }

    //Setter/Getter
    public void setTool(Tool tool) {
        this.tool = tool;
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public Tool getTool() {
        return tool;
    }
    public int getFavorTokens() {
        return favorTokens;
    }

    //Identificazione
    public boolean isSame(ToolCard obj) {
        return getName().equals(obj.getName()) && getDescription().equals(obj.getDescription()) && tool == obj.getTool() & favorTokens == obj.getFavorTokens();
    }
}
