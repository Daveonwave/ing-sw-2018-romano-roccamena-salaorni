package mvc.model.objects.toolcards;

import mvc.model.objects.Die;
import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;

import java.util.List;

public class DiluentePerPastaCalda extends ToolCard {

    public DiluentePerPastaCalda(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    //Usa carta strumento
    public void useToolCard(Match match, Player player, List<Die> dice) {
    }
}
