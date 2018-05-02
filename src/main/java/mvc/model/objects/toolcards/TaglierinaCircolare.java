package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;

public class TaglierinaCircolare extends ToolCard {

    public TaglierinaCircolare(String name, String description, Match match, int favorTokens, DieColor dieColor) {
        super(name, description, match, favorTokens, dieColor);
    }

    //Usa carta strumento
    public void useToolCard(Match newMatch, Player player) {
    }
}
