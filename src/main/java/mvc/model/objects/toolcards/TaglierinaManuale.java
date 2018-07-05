package mvc.model.objects.toolcards;

import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.model.objects.enums.DieColor;

import java.rmi.RemoteException;

/**
 * Specific tool card
 */
public class TaglierinaManuale extends ToolCard {

    /**
     * Class constructor
     */
    public TaglierinaManuale() {
        super("taglierina manuale", "Muovi fino a due dadi, dello stesso colore di un solo dado del tracciato dei round, rispettando tutte le restrizioni", DieColor.BLUE);
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
        Window window = player.getWindow();
        Cell origin1 = player.getWindow().retrieveCell(input.getOriginCell1());
        Cell origin2 = null;
        Cell destination2 = null;
        boolean sameColor = false;
        if(input.getOriginCell2() != null) {
            origin2 = player.getWindow().retrieveCell(input.getOriginCell2());
            destination2 = player.getWindow().retrieveCell(input.getDestinationCell2());
        }
        Cell destination1 = player.getWindow().retrieveCell(input.getDestinationCell1());


        //Controlla correttezza uso
        for(int i = 1; i<match.getTurnHandler().getRound(); i++) {
           for(Die die: match.getRoundTrack().retrieveDice(i)){
               if((origin2 == null && origin1.getDie().sameColor(die))
                       || (origin2 != null && origin1.getDie().sameColor(die) && origin2.getDie().sameColor(die))) {
                   sameColor = true;
                   break;
               }
           }
           if(sameColor){
               break;
           }
        }
        if(!sameColor)
            throw new MatchException("il colore non è nel tracciato dei round");


        //Esegue gli scambi
        window.moveDie(origin1, destination1);
        try {
            if(origin2 != null) {
                window.moveDie(origin2, destination2);
            }
        } catch (MatchException e) {
            window.moveDie(destination1, origin1);
            throw e;
        }
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
     * Use this specific tool card in a multi player match
     * @param match object match
     * @param input input of the tool card
     * @throws RemoteException
     */
    public void useToolCard(SinglePlayerMatch match, ToolCardInput input) throws RemoteException {
        cardEffect(match, match.getPlayer(), input);
    }
}
