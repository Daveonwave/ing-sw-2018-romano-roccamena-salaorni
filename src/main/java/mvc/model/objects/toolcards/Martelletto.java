package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.rmi.RemoteException;

public class Martelletto extends ToolCard {

    //Costruttori
    public Martelletto() {
        super("martelletto", "tira nuovamente i dadi della riserva; puo essere giocata solo al secondo turno di un round prima di estrarre il dado", GameConstants.BLUE);

    }

    private void cardEffect(Match match, Player player, ToolCardInput input) throws RemoteException {
        //Controlla se l'utilizzo è possibile
        if (match.getTurnHandler().isFirstTurnWave() || !player.getTurnDiePlaced())
            throw new MatchException("non puoi usare questa carta ora");

        //Rilancia i dadi della riserva
        for (Die die : match.getMatchDice().getDraftPool())
            die.roll();
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getTurnPlayer(), input);
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
