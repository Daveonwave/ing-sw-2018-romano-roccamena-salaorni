package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class PennelloPerPastaSalda extends ToolCard {

    /**
     * Class constructor
     */
    public PennelloPerPastaSalda() {
        super("pennello per pasta salda", "Dopo aver scelto un dado, tira nuovamente quel dado; se non puoi piazzarlo, riponilo nella riserva", DieColor.PURPLE);

    }

    /**
     * Handle of the specific tool card
     * @param match object match
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChosenDie());

        //Controlla giocatore non ha gia piazzato questo turno
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Rilancia il dado
        die.roll();

        //Controlla se il dado è piazzabile
        if (player.getWindow().getValidCells(die).isEmpty()) {
            player.setTurnDiePlaced(true);
            throw new MatchException("non puoi piu piazzare il dado");
        }

        //Aggiorna effetti
        player.getToolCardEffect().setChosenDie(die);
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
