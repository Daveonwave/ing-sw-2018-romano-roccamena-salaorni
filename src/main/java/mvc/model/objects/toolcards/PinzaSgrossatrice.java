package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.rmi.RemoteException;

public class PinzaSgrossatrice extends ToolCard {

    //Costruttori
    public PinzaSgrossatrice() {
        super("pinza sgrossatrice", "dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di uno", GameConstants.PURPLE);
    }


    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());
        boolean increaseShade = input.getIncreaseShade();

        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if (die.getShade() == 1 || die.getShade() == 6)
            throw new MatchException("non puoi usare questa carta per quel dado");

        //Esegue effetto
        if (increaseShade)
            die.setShade(die.getShade()+1);
        else
            die.setShade(die.getShade()-1);

        //Assegna la scelta del dado
        player.getToolCardEffect().setChoosenDie(die);
    }
}
