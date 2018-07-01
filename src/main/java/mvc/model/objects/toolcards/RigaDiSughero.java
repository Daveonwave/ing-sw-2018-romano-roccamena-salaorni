package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

public class RigaDiSughero extends ToolCard {

    //Costruttori
    public RigaDiSughero() {
        super("riga di sughero", "Dopo aver scelto un dado, piazzalo in una cella che non sia adiacente ad un altro, rispettando tutte le altre restrizioni", DieColor.YELLOW);
    }

    private void cardEffect(Player player) throws RemoteException {
        //Controllo correttezza uso
        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        //Esegue effetto carta
        player.getToolCardEffect().setIgnoreIsolatedRestriction(true);
    }

    //Usa carta strumento
    public void useToolCard(MultiPlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getTurnPlayer());
    }
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match.getPlayer());
    }
}

