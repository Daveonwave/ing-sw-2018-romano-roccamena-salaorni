package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;

import java.rmi.RemoteException;

public class RigaDiSughero extends ToolCard {

    //Costruttori
    public RigaDiSughero() {
        super("riga di sughero", "dopo aver scelto un dado, piazzalo in una cella che non sia adiacente ad un altro, rispettando tutte le altre restrizioni", 0, GameConstants.YELLOW);

    }
    //Usa carta strumento
    public void useToolCard(Match match, ToolCardInput input) throws RemoteException {
        //Ottiene dati
        Player player = match.getTurnPlayer();

        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Esegue effetto carta
        player.getToolCardEffect().setIgnoreAdjacentCellsRestriction(true);
    }
}

