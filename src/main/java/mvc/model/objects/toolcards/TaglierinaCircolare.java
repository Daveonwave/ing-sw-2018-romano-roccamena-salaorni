package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;

import java.awt.*;

public class TaglierinaCircolare extends ToolCard {

    public TaglierinaCircolare(String name, String description, int favorTokens, Color color) {
        super(name, description, favorTokens, color);

    }

    //Usa carta strumento
    public void useToolCard(Match newMatch, Player player) {
    }
}
