package mvc.model.objects.toolcards;

import mvc.model.objects.Die;
import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

import java.util.List;

public class PinzaSgrossatrice extends ToolCard {

    private boolean increase;

    public PinzaSgrossatrice(String name, String description, int favorTokens, DieColor dieColor) {
        super(name, description, favorTokens, dieColor);
    }

    //Usa carta strumento
    public void useToolCard(Match match, Player player, List<Die> dice) {
        if (increase){
            match.getDraftPool().get(0).setShade(match.getDraftPool().get(0).getShade()+1);
        }
        else{
            match.getDraftPool().get(0).setShade(match.getDraftPool().get(0).getShade()-1);
        }
    }
}
