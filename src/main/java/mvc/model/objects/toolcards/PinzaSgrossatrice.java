package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class PinzaSgrossatrice extends ToolCard {

    //Costruttori
    public PinzaSgrossatrice() {
        super("pinza sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di uno", DieColor.PURPLE);
    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());
        boolean increaseShade = input.getIncreaseShade();

        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if ((die.getShade() == 1 && !input.getIncreaseShade()) || (die.getShade() == 6 && input.getIncreaseShade()))
            throw new MatchException("non puoi usare questa carta per quel dado");

        //Esegue effetto
        if (increaseShade)
            die.setShade(die.getShade()+1);
        else
            die.setShade(die.getShade()-1);

        //Assegna la scelta del dado
        player.getToolCardEffect().setChoosenDie(die);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
