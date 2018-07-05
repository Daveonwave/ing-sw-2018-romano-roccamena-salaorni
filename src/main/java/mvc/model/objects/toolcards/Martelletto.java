package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class Martelletto extends ToolCard {

    /**
     * Class Constructor
     */
    public Martelletto() {
        super("martelletto", "Tira nuovamente i dadi della riserva; puo essere giocata solo al secondo turno di un round prima di estrarre il dado", DieColor.BLUE);

    }

    /**
     * Handle the effect of the specific tool card
     * @param match object match
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Controlla se l'utilizzo è possibile
        if (match.getTurnHandler().isFirstTurnWave() || player.getTurnDiePlaced())
            throw new MatchException("non puoi usare questa carta ora");

        //Rilancia i dadi della riserva
        for (Die die : match.getMatchDice().getDraftPool())
            die.roll();
    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
