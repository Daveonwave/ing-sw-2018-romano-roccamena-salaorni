package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public class PennelloPerEglomise extends ToolCard {

    public PennelloPerEglomise(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    @Override
    public void useToolCard(Match match) {
        int xPosition = 0,yPosition = 0;
        match.getPlayers().get(0).getWindow().getCells()[0][0].getDie().setxPosition(xPosition);
        match.getPlayers().get(0).getWindow().getCells()[0][0].getDie().setyPosition(yPosition);
    }
}
