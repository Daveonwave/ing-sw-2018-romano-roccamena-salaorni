package mvc.model.objects;

import mvc.exceptions.MatchException;

import java.rmi.RemoteException;
import java.util.List;

public class SinglePlayerMatch extends Match {
    //Partita singleplayer del gioco

    private Player player;

    //Costruttori
    public SinglePlayerMatch(Player player, List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, MatchDice matchDice, RoundTrack roundTrack) {
        super(objectiveCards, toolCards, matchDice, roundTrack, new SinglePlayerTurnHandler());
        this.player = player;
    }

    //Setter/Getter
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    //Mossa di scelta di una finestra
    public void chooseWindow(Window window) throws RemoteException {
        //Controllo stato corretto della partita e della finestra scelta
        if (matchState != MatchState.CHOOSE_WINDOWS || player.getWindow() != null)
            throw new MatchException("non puoi scegliere una finestra ora");

        //Assegna vetrata e favor tokens
        player.setWindow(window);
        player.setFavorTokens(window.getDifficulty());

        //Aggiorna stato prossimo
        turnHandler.startRounds();

        matchState = MatchState.PLAY_ROUND;
    }
    //Mossa di piazzamento di un dado in una finestra
    public void placeDie(Cell cell, Die die) throws RemoteException{
        //Controllo stato corretto partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi piazzare un dado ora");

        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if (player.getToolCardEffect().getChoosenDie() != null)
            if (!player.getToolCardEffect().getChoosenDie().sameDie(die))
                throw new MatchException("non puoi scegliere quel dado");

        //Posiziona il dado
        player.getWindow().placeDie(cell, die);

        matchDice.getDraftPool().remove(die);

        //Aggiorna segnali
        if (!player.getToolCardEffect().getReplaceDie())
            player.setTurnDiePlaced(true);
        else
            player.getToolCardEffect().setReplaceDie(false);

        player.getToolCardEffect().setChoosenDie(null);
        player.getToolCardEffect().setIgnoreAdjacentCellsRestriction(false);
    }
    //Mossa di utilizzo di una carta strumento
    public void useToolCard(ToolCardInput input, ToolCard toolCard) throws RemoteException {
        //Controllo correttezza stato partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi usare una carta strumento ora");

        //Controlla correttezza utilizzo
        boolean ok = input.getPayDie().getColor().equals(toolCard.getColor());
        if (!ok)
            throw new MatchException("non pui spendere quel dado per quella carta");

        //Utilizza la carta strumento
        toolCard.useToolCard(this, input);

        //Elimina il dado
        matchDice.getDraftPool().remove(input.getPayDie());

        //Elimina la carta strumento
        toolCards.remove(toolCard);
    }
    //Mossa di fine del turno
    public void endTurn(Player player) throws RemoteException {
        //Controllo stato corretto della partita
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi terminare il turno ora");

        //Controllo se ultimo turno
        if (!turnHandler.isLastTurn()) {
            //Calcola un nuovo turno
            turnHandler.nextTurn();

            //Controlla se giocatore deve saltare turno
            if (getPlayer().getToolCardEffect().getSkipTurn()) {
                turnHandler.nextTurn();
                getPlayer().getToolCardEffect().setSkipTurn(false);
            }

            //Il player potrà piazzare un nuovo dado
            player.setTurnDiePlaced(false);

            //Se nuovo round sposta draft pool sulla round track e crea nuova draft pool
            if (turnHandler.isRoundFirstTurn()) {
                roundTrack.moveDice(matchDice, turnHandler.getRound()-1);
                matchDice.extractDraftPoolFromBag();
            }
        } else {
            //Calcola un nuovo turno
            turnHandler.nextTurn();

            //Aggiorna stato prossimo
            matchState = MatchState.ENDED;
        }
    }
}
