package mvc.model.objects.toolcards;

import mvc.model.objects.Die;
import mvc.model.objects.Match;
import mvc.model.objects.ToolCard;
import mvc.model.objects.enums.DieColor;
import mvc.model.objects.enums.Tool;

public class TamponeDiamantato implements mvc.model.objects.toolcards.ToolCard{


    public void effectToolCard(Match match) {
        match.getDraftPool().get(0).oppositeShade();
    }
}
