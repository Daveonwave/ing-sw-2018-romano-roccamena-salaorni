package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class TenagliaRotelle extends ToolCard {

    //Costruttori
    public TenagliaRotelle() {
        super("Tenaglia a Rotelle", "Dopo il tuo primo turno scegli subito un altro dado; salta il tuo secondo turno in questo round", DieColor.RED);
    }

    private void cardEffect(Match match, Player player) throws RemoteException {
        //Controlla correttezza uso
        if (!match.getTurnHandler().isFirstTurnWave())
            throw new MatchException("puoi usare questa carta solo al tuo secondo turno");
        if (!player.getTurnDiePlaced())
            throw new MatchException("prima scegli il tuo primo dado");

        player.getToolCardEffect().setReplaceDie(true);
        player.getToolCardEffect().setSkipTurn(true);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer());
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer());

    }
}
