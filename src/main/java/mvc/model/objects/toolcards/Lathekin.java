package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class Lathekin extends ToolCard {

    /**
     * Cell constructor
     */
    public Lathekin() {
        super("lathekin", "Muovi esattamente due dadi rispettando tutte le restrizioni", DieColor.YELLOW);
    }

    /**
     * Handle the effect of the specific tool card
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Window window = player.getWindow();
        Cell origin1 = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell origin2 = player.getWindow().retrieveCell(input.getOriginCell2());
        Cell destination1 = player.getWindow().retrieveCell(input.getDestinationCell1());
        Cell destination2 = player.getWindow().retrieveCell(input.getDestinationCell2());

        //Esegue gli scambi
        window.moveDie(origin1, destination1);
        try {
            window.moveDie(origin2, destination2);
        } catch (MatchException e) {
            window.moveDie(destination1, origin1);
            throw e;
        }
    }

    //Usa carta strumento
    /**
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer(), input);
    }
    /**
     * Use this specific tool card in a single player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer(), input);
    }
}
