package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.awt.Color;
import java.rmi.RemoteException;

public class Martelletto extends ToolCard {

    //Costruttori
    public Martelletto(String name, String description, int favorTokens, Color color) {
        super("martelletto", "tira nuovamente i dadi della riserva; puo essere giocata solo al secondo turno di un round prima di estrarre il dado", favorTokens, color);

    }

    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();

        //Controlla se l'utilizzo è possibile
        if (match.getTurnHandler().isFirstTurnWave() || !player.getTurnDiePlaced())
            throw new MatchException("non puoi usare questa carta ora");

        //Rilancia i dadi della riserva
        for (Die die : match.getMatchDice().getDraftPool())
            die.roll();
    }
}
