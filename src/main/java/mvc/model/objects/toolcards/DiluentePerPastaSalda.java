package mvc.model.objects.toolcards;

import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class DiluentePerPastaSalda extends ToolCard {

    /**
     * Class Constructor
     */
    public DiluentePerPastaSalda() {
        super("diluente per pasta salda", "Dopo aver scelto un dado, riponilo del sacco di dadi ed estrai un altro dado; scegli il valore del nuovo dado e piazzalo rispettando tutte le restrizioni", DieColor.PURPLE);
    }

    /**
     * Handle the effect of the specific tool card
     * @param match object match
     * @param player player that uses it
     * @param input cell or die pre-selected
     * @throws RemoteException
     */
    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChosenDie());

        //Toglie il dado e lo rimette nel sacco
        match.getMatchDice().getDraftPool().remove(die);
        match.getMatchDice().getDiceBag().add(die);

        //Estrae nuovo dado, seleziona valore scelto e viene impostato come dado scelto
        die = match.getMatchDice().extractDieFromBag();
        die.setShade(input.getChosenShade());
        match.getMatchDice().getDraftPool().add(die);

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
