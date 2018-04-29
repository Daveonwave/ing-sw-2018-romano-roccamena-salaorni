package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;

public class AlesatorePerLaminaDiRame extends ToolCard{

    public AlesatorePerLaminaDiRame(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    //Usa carta strumento
    public void useToolCard(Match match, Player player) {
    }
}
