package mvc.model.objects;

import mvc.exceptions.AppModelException;
import mvc.exceptions.MatchException;

import java.rmi.RemoteException;
import java.util.List;

public class Match {
    //Partita del gioco

    private MatchState matchState;
    private List<Player> players;
    private TurnHandler turnHandler;
    private List<PublicObjectiveCard> publicObjectiveCards;
    private List<ToolCard> toolCards;
    private MatchDice matchDice;
    private RoundTrack roundTrack;

    //Costruttori
    public Match(Match match) {
        this(match.getPlayers(), match.getPublicObjectiveCards(), match.getToolCards(), match.getMatchDice(), match.getRoundTrack());
    }
    //MultiPlayer
    public Match(List<Player> players, List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, MatchDice matchDice, RoundTrack roundTrack) {
        this.matchState = MatchState.STARTED;
        this.players = players;
        this.turnHandler = new TurnHandler(players.size(), 0);
        this.publicObjectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.matchDice = matchDice;
        this.roundTrack = roundTrack;
    }
    //SinglePlayer
    public Match(Player player, List<PublicObjectiveCard> objectiveCards, List<ToolCard> toolCards, MatchDice matchDice, RoundTrack roundTrack) {
        this.matchState = MatchState.STARTED;
        this.players.add(player);
        this.turnHandler = new TurnHandler(players.size(), 0);
        this.publicObjectiveCards = objectiveCards;
        this.toolCards = toolCards;
        this.matchDice = matchDice;
        this.roundTrack = roundTrack;
    }

    //Setter/Getter
    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setTurnHandler(TurnHandler turnHandler) {
        this.turnHandler = turnHandler;
    }
    public void setPublicObjectiveCards(List<PublicObjectiveCard> publicObjectiveCards) {
        this.publicObjectiveCards = publicObjectiveCards;
    }
    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }
    public void setMatchDice(MatchDice matchDice) {
        this.matchDice = matchDice;
    }
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
    }

    public MatchState getMatchState() {
        return matchState;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public TurnHandler getTurnHandler() {
        return turnHandler;
    }
    public List<PublicObjectiveCard> getPublicObjectiveCards() {
        return publicObjectiveCards;
    }
    public List<ToolCard> getToolCards() {
        return toolCards;
    }
    public MatchDice getMatchDice() {
        return matchDice;
    }
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    //Ottiene carta strumento
    public synchronized ToolCard retrieveToolCard(ToolCard toolCard) throws RemoteException {
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

        //TODO: punti token e persi per celle vuote

        return new PlayerPoints(privatePoints, publicPoints, favorsPoints, openLost);
    }

    //Ottiene giocatore che gioca per primo al round corrente
    public Player getFirstPlayer() {
        return players.get(turnHandler.getFirstPlayerIndex());
    }
    //Ottiene giocatore del turno corrente del round corrente
    public Player getTurnPlayer() {
        return players.get(turnHandler.getTurnPlayerIndex());
    }
    //Ottiene se un giocatore si trova nel suo turno
    public boolean isPlayerTurn(Player player) {
        return getTurnPlayer().samePlayer(player);
    }

    //Inizia partita
    public void beginMatch() throws RemoteException {
        //Controllo stato corretto della partita
        if (matchState != MatchState.STARTED)
            throw new MatchException("la partita è gia iniziata");

        //Aggiorna lo stato prossimo
        matchState = MatchState.CHOOSE_WINDOWS;
    }
    //Mossa di scelta di una finestra
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
    public void placeDie(Player player, Cell cell, Die die) throws RemoteException{
        //Controllo stato corretto partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi piazzare un dado ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        if (player.getTurnDiePlaced())
            throw new MatchException("hai gia piazzato un dado");

        if (player.getToolCardEffect().getChoosenDie() != null)
            if (!player.getToolCardEffect().getChoosenDie().sameDie(die))
                throw new MatchException("non puoi scegliere quel dado");

        //Posiziona il dado
        player.getWindow().placeDie(cell, die);

        //Aggiorna segnali
        player.setTurnDiePlaced(true);
        player.getToolCardEffect().setChoosenDie(null);
        player.getToolCardEffect().setIgnoreAdjacentCellsRestriction(false);
    }
    //Mossa di utilizzo di una carta strumento
    public void useToolCard(Player player, ToolCardInput input, ToolCard toolCard) throws RemoteException {
        //Controllo correttezza stato partita e parametri
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi usare una carta strumento ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        boolean firstUse = player.getFavorTokens() == 0;
        if (firstUse)
            if (player.getFavorTokens() < 1)
                throw new MatchException("punti favore insufficenti");
        else
            if (player.getFavorTokens() < 2)
                throw new MatchException("punti favore insufficenti");

        //Utilizza la carta strumento
        toolCard.useToolCard(this, input);

        //Il giocatore paga i favor tokens
        if (firstUse)
            player.setFavorTokens(player.getFavorTokens()-1);
        else
            player.setFavorTokens(player.getFavorTokens()-2);

    }
    //Mossa di fine del turno
    public void endTurn(Player player) throws RemoteException {
        //Controllo stato corretto della partita
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi terminare il turno ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        //Controllo se ultimo turno
        if (!turnHandler.isLastTurn()) {
            //Calcola un nuovo turno
            turnHandler.nextTurn();

            //Il player potrà piazzare un nuovo dado
            getTurnPlayer().setTurnDiePlaced(false);

            //Se nuovo round sposta draft pool sulla round track e crea nuova draft pool
            if (turnHandler.isRoundFirstTurn()) {
                roundTrack.moveDice(matchDice, turnHandler.getRound()-1);
                matchDice.extractDraftPoolFromBag();
            }
        } else {
            //Aggiorna stato prossimo
            matchState = MatchState.ENDED;
        }
    }
}
