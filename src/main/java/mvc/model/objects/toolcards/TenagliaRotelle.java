package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class TenagliaRotelle extends ToolCard {

    /**
     * Class constructor
     */
    public TenagliaRotelle() {
        super("tenaglia a rotelle", "Dopo il tuo primo turno scegli subito un altro dado; salta il tuo secondo turno in questo round", DieColor.RED);
    }

    /**
     * Handle the effect of the specific tool card
     * @param match object match
     * @param player player that uses it
     * @throws RemoteException
     */
    private void cardEffect(Match match, Player player) throws RemoteException {
        //Controlla correttezza uso
        if (!match.getTurnHandler().isFirstTurnWave())
            throw new MatchException("puoi usare questa carta solo al tuo secondo turno");
        if (!player.getTurnDiePlaced())
            throw new MatchException("prima scegli il tuo primo dado");
        MultiPlayerTurnHandler turnHandler =((MultiPlayerTurnHandler) match.getTurnHandler());
        if(turnHandler.getTurnPlayerIndex() == turnHandler.leftIndexShift(turnHandler.getFirstPlayerIndex())){
            throw new MatchException("non puoi usare questa tool card perchè il prossimo turno è il tuo");
        }

        player.getToolCardEffect().setReplaceDie(true);
        player.getToolCardEffect().setSkipTurn(true);
    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer());
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer());

    }
}
