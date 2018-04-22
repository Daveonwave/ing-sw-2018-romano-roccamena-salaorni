package mvc.model.objects;

import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public class ToolCard extends Card{
    //Carta strumento

    private Tool tool;
    private int favorTokens;
    private DieColor dieColor;

    //Costruttore
    public ToolCard(String name, String description, Tool tool, int favorTokens, DieColor dieColor) {
        super(name, description);
        this.tool = tool;
        this.favorTokens = favorTokens;
        this.dieColor = dieColor;
    }

    //Setter/Getter
    public void setTool(Tool tool) {
        this.tool = tool;
    }
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }
    public void setDieColor(DieColor dieColor) {
        this.dieColor = dieColor;
    }

    public Tool getTool() {
        return tool;
    }
    public int getFavorTokens() {
        return favorTokens;
    }
    public DieColor getDieColor() {
        return dieColor;
    }

}
