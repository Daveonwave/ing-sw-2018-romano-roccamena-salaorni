package mvc.model.objects.toolcards;

import mvc.model.objects.Match;
import mvc.model.objects.MatchDice;
import mvc.model.objects.Player;
import mvc.model.objects.ToolCard;

import java.awt.*;

public class PinzaSgrossatrice extends ToolCard {

    private boolean increase;

    public PinzaSgrossatrice(String name, String description, int favorTokens, Color color) {
        super(name, description, favorTokens, color);
    }

    //Usa carta strumento
    public void useToolCard(Match match, Player player) {
        if (increase){
            match.getMatchDice().getDraftPool().get(0).setShade(match.getMatchDice().getDraftPool().get(0).getShade()+1);
        }
        else{
            match.getMatchDice().getDraftPool().get(0).setShade(match.getMatchDice().getDraftPool().get(0).getShade()-1);
        }
    }
}
