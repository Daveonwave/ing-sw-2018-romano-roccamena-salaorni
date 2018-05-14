package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.awt.Color;
import java.rmi.RemoteException;

public class TaglierinaCircolare extends ToolCard {

    //Costruttori
    public TaglierinaCircolare(String name, String description, int favorTokens, Color color) {
        super("taglierina circolare", "dopo aver scelto un dado, scambia quel dado con uno nel tracciato dei round", favorTokens, color);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();
        Die die = match.getMatchDice().retrieveDieFromDraftPool(input.getChoosenDie());

        //Controlla giocatore non ha gia piazzato questo turno
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Esegue effetto carta
        Die roundTrackDie = match.getRoundTrack().retrieveDie(input.getRoundTrackRound(), input.getRoundTrackDie());

        match.getMatchDice().getDraftPool().remove(die);
        match.getMatchDice().getDraftPool().add(roundTrackDie);
        match.getRoundTrack().retrieveDice(match.getTurnHandler().getRound()).add(die);

        //Aggiorna effetti
        player.getToolCardEffect().setChoosenDie(roundTrackDie);
    }
}
