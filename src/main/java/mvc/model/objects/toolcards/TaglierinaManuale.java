package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;

import java.awt.*;

public class TaglierinaManuale extends ToolCard {

<<<<<<< HEAD
    public TaglierinaManuale(String name, String description, int favorTokens, Color color) {
        super(name, description, favorTokens, color);
=======
    public TaglierinaManuale(String name, String description, Match match, int favorTokens, DieColor dieColor) {
        super(name, description, match, favorTokens, dieColor);
>>>>>>> origin/master
    }

    //Usa carta strumento
    public void useToolCard(Match newMatch, Player player) {
    }
}
