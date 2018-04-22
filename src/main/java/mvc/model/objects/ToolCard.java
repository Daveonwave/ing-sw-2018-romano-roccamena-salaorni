package mvc.model.objects;

public class ToolCard extends Card{
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
}
