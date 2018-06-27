package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class TaglierinaCircolare extends ToolCard {

    //Costruttori
    public TaglierinaCircolare() {
        super("taglierina circolare", "Dopo aver scelto un dado, scambia quel dado con uno nel tracciato dei round", DieColor.GREEN);

    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

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
        player.getToolCardEffect().setChoosenDie(roundTrackDie);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
