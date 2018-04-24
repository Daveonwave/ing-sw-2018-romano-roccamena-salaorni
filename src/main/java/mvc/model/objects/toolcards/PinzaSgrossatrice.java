package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public class PinzaSgrossatrice extends ToolCard {

    private boolean increase;

    public PinzaSgrossatrice(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    @Override
    public void useToolCard(Match match) {
            if (increase){
                match.getDraftPool().get(0).setShade(match.getDraftPool().get(0).getShade()+1);
            }
            else{
                match.getDraftPool().get(0).setShade(match.getDraftPool().get(0).getShade()-1);
            }
    }
}
