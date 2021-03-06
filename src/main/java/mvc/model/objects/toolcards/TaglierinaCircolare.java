package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class TaglierinaCircolare extends ToolCard {

    /**
     * Class constructor
     */
    public TaglierinaCircolare() {
        super("taglierina circolare", "Dopo aver scelto un dado, scambia quel dado con uno nel tracciato dei round", DieColor.GREEN);

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

        //Controlla giocatore non ha gia piazzato questo turno
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado questo turno");

        //Esegue effetto carta
        Die roundTrackDie = match.getRoundTrack().retrieveDie(input.getRoundTrackRound(), input.getRoundTrackDie());
        match.getMatchDice().getDraftPool().remove(die);
        match.getMatchDice().getDraftPool().add(roundTrackDie);
        match.getRoundTrack().retrieveDice(input.getRoundTrackRound()).remove(roundTrackDie);
        match.getRoundTrack().retrieveDice(input.getRoundTrackRound()).add(die);


        //Aggiorna effetti
        player.getToolCardEffect().setChosenDie(roundTrackDie);
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
