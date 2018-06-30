package mvc.model.objects;

import mvc.controller.handlers.TimedTurnHandler;
import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Multiplayer extension of match entity
 */
public class MultiPlayerMatch extends Match {
    //Partita multiplayer del gioco

    private List<Player> players;
    private transient TimedTurnHandler timedTurnHandler;

    //Costruttori

    /**
     * Create new match from a given match
     * @param match Match instance
     */
    public MultiPlayerMatch(MultiPlayerMatch match) {
        this(match.getPlayers(), match.getPublicObjectiveCards(), match.getToolCards(), match.getMatchDice(), match.getRoundTrack());
    }
    /**
     * Create new match
     * @param players Players intances
     * @param objectiveCards Objective cards instanced
     * @param toolCards Tool cards instances
     * @param matchDice Match dice instance
     * @param roundTrack Round track ntance
     */
    public MultiPlayerMatch(List<Player> players, List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, MatchDice matchDice, RoundTrack roundTrack) {
        super(objectiveCards, toolCards, matchDice, roundTrack, new MultiPlayerTurnHandler(players.size(), 0));
        this.players = players;
        this.timedTurnHandler = null;
    }


    //Setter/Getter
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setTimedTurnHandler(TimedTurnHandler timedTurnHandler) {
        this.timedTurnHandler = timedTurnHandler;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public TimedTurnHandler getTimedTurnHandler() {
        return timedTurnHandler;
    }


    //Ottiene giocatore
    /**
     * Obtain player reference from given player state
     * @param player Player instance
     * @return
     * @throws RemoteException MatchException for invalid argument passed
     */
    public Player retrievePlayer(Player player) throws RemoteException {
        //Controlla correttezza
        if (player==null)
            throw new MatchException("giocatore non valido");

        //Ottiene giocatore
        Player result = null;
        for (Player p : players) {
            if (p.samePlayer(player)) {
                result = p;
                break;
            }
        }

        if (result==null)
            throw new MatchException("giocatore non trovato");

        return result;
    }
    //Ottiene carta strumento
    /**
     *
     * @param toolCard
     * @return
     * @throws RemoteException
     */
    public ToolCard retrieveToolCard(ToolCard toolCard) throws RemoteException {
        //Controlla correttezza
        if (toolCard==null)
            throw new MatchException("carta non valida");

        //Ottiene carta
        ToolCard result = null;
        for (ToolCard c : toolCards) {
            if (c.sameCard(toolCard)) {
                result = c;
                break;
            }
        }
        if (result == null)
            throw new AppModelException("carta strumento non valida");

        return result;
    }

    //Calcolo punteggio di un giocatore
    public PlayerPoints getPlayerPoints(Player player) {
        int privatePoints = 0;
        int publicPoints = 0;
        int favorsPoints = 0;
        int openLost = 0;

        //Obiettivi privati
        for (PrivateObjectiveCard card : player.getPrivateObjectiveCards())
            privatePoints += card.getPoints(player.getWindow());

        //Obiettivi pubblici
        for (PublicObjectiveCard card : publicObjectiveCards)
            publicPoints += card.getPoints(player.getWindow());

        for(Cell[] cells : player.getWindow().getCells()){
            for(Cell cell : cells){
                if(cell.getDie() == null){
                    openLost -= 1;
                }
            }
        }

        favorsPoints = player.getFavorTokens();

        return new PlayerPoints(privatePoints, publicPoints, favorsPoints, openLost);
    }


    //Ottiene giocatore che gioca per primo al round corrente
    /**
     * Obtain first player of current round
     * @return
     */
    public Player getFirstPlayer() {
        return players.get(((MultiPlayerTurnHandler) turnHandler).getFirstPlayerIndex());
    }
    //Ottiene giocatore del turno corrente del round corrente
    /**
     * Obtain current turn player of current round
     * @return
     */
    public Player getTurnPlayer() {
        return players.get(((MultiPlayerTurnHandler) turnHandler).getTurnPlayerIndex());
    }
    //Ottiene se un giocatore si trova nel suo turno
    /**
     * Assert if a given player is current turn player
     * @param player
     * @return
     */
    public boolean isPlayerTurn(Player player) {
        return getTurnPlayer().samePlayer(player);
    }


    //Mossa di abbandono di una partita
    /**
     * Set inactivity of a player to the match
     * @param player Player instance
     * @throws MatchException Invalid player passed
     */
    public void leaveMatch(Player player) throws MatchException {
        //Controllo stato corretto della partita
        if (turnHandler.isEnded() || !player.isActive())
            throw new MatchException("non puoi abbandonare ora");

        //Abbandona la partita
        player.setActive(false);
    }
    //Mossa di ripartecipazione ad una partita
    /**
     * Rebind a player as active relative to the match
     * @param player Player instance
     * @throws MatchException MatchException for invalid argument passed or invalid match state for the action
     */
    public void rejoinMatch(Player player) throws MatchException {
        //Controllo stato corretto della partita
        if (turnHandler.isEnded() || player.isActive())
            throw new MatchException("non puoi ripartecipare alla partita ora");

        //Abbandona la partita
        player.setActive(true);
    }
    //Mossa di scelta di una finestra
    /**
     * Player choose a window for the match
     * @param player Player instance
     * @param window Window instance
     * @throws RemoteException MatchException for invalid argument passed or invalid match state for the action
     */
    public void chooseWindow(Player player, Window window) throws RemoteException {
        //Controllo stato corretto della partita e della finestra scelta
        if (matchState != MatchState.CHOOSE_WINDOWS || player.getWindow() != null)
            throw new MatchException("non puoi scegliere una finestra ora");

        //Assegna vetrata e favor tokens
        player.setWindow(window);
        player.setFavorTokens(window.getDifficulty());

        //Calcola se tutti hanno la propria finestra assegnata
        boolean nextStep = true;
        for (Player p : players) {
            if (p.getWindow() == null) {
                nextStep = false;
                break;
            }
        }

        //Aggiorna stato prossimo
        if (nextStep) {
            turnHandler.startRounds();

            matchState = MatchState.PLAY_ROUND;
        }
    }
    //Mossa di piazzamento di un dado in una finestra
    /**
     * Player place a die in a cell
     * @param player Player instance
     * @param cell Cell instance of player window
     * @param die Die instance
     * @throws RemoteException MatchException for invalid argument passed or invalid match state for the action
     */
    public void placeDie(Player player, Cell cell, Die die) throws RemoteException{
        //Controllo stato corretto partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi piazzare un dado ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        if (!player.getToolCardEffect().getReplaceDie() && player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if (player.getToolCardEffect().getChosenDie() != null && !player.getToolCardEffect().getChosenDie().sameDie(die))
            throw new MatchException("non puoi scegliere quel dado");

        //Posiziona il dado
        player.getWindow().placeDie(cell, die);

        matchDice.getDraftPool().remove(die);

        //Aggiorna segnali
        if (!player.getToolCardEffect().getReplaceDie())
            player.setTurnDiePlaced(true);
        else
            player.getToolCardEffect().setReplaceDie(false);

        player.getToolCardEffect().setChosenDie(null);
        player.getToolCardEffect().setIgnoreAdjacentCellsRestriction(false);
    }
    //Mossa di utilizzo di una carta strumento
    /**
     * Player uses a tool card with a given input
     * @param player Player instance
     * @param input Tool card input instance
     * @param toolCard Tool card instance
     * @throws RemoteException MatchException for invalid argument passed or invalid match state for the action
     */
    public void useToolCard(Player player, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        //Controllo correttezza stato partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi usare una carta strumento ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        if (player.getTurnToolCardUsed())
            throw new MatchException("hai gia usato una carta strumento");

        boolean firstUse = toolCard.getFavorTokens() == 0;
        if (firstUse)
            if (player.getFavorTokens() < 1)
                throw new MatchException("punti favore insufficenti");
        else
            if (player.getFavorTokens() < 2)
                throw new MatchException("punti favore insufficenti");

        //Utilizza la carta strumento
        toolCard.useToolCard(this, input);

        //Il giocatore paga i favor tokens
        if (firstUse) {
            player.setFavorTokens(player.getFavorTokens() - 1);
            toolCard.setFavorTokens(toolCard.getFavorTokens() + 1);
        } else {
            player.setFavorTokens(player.getFavorTokens() - 2);
            toolCard.setFavorTokens(toolCard.getFavorTokens() + 2);
        }

        //Il giocatore non puo piu usare carte questo turno
        player.setTurnToolCardUsed(true);
    }
    //Mossa di fine del turno
    /**
     * End current player turn and calculate next match state
     * @param player Player instance
     * @throws RemoteException MatchException for invalid argument passed or invalid match state for the action
     */
    public void endTurn(Player player) throws RemoteException {
        //Controllo stato corretto della partita
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi terminare il turno ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        //Se giocatore è legato ad un dado per l'effetto di una carta, viene liberato dal vincolo
        player.getToolCardEffect().setChosenDie(null);

        //Il giocatore potra utilizzare di nuovo carte in turni successivi
        player.setTurnToolCardUsed(false);

        //Controllo se ultimo turno
        if (!turnHandler.isLastTurn()) {
            //Calcola un nuovo turno
            turnHandler.nextTurn();

            //Controlla se giocatore deve saltare turno
            if (getTurnPlayer().getToolCardEffect().getSkipTurn()) {
                turnHandler.nextTurn();
                getTurnPlayer().getToolCardEffect().setSkipTurn(false);
            }

            //Il giocatore potrà piazzare un nuovo dado
            getTurnPlayer().setTurnDiePlaced(false);

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
