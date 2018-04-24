package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public class TaglierinaManuale extends ToolCard {

    public TaglierinaManuale(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    @Override
    public void useToolCard(Match match) {

    }
}
