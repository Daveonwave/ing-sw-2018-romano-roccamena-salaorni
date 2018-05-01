package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;

import java.awt.*;

public class DiluentePerPastaCalda extends ToolCard {

    public DiluentePerPastaCalda(String name, String description, int favorTokens, Color color) {
        super(name, description, favorTokens, color);
    }

    //Usa carta strumento
    public void useToolCard(Match match, Player player) {
    }
}
