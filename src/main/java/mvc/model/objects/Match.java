package mvc.model.objects;

import mvc.exceptions.MatchException;
import mvc.model.objects.enums.MatchState;

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
    public Match(Match match) {
        this(match.getPlayers(), match.getPublicObjectiveCards(), match.getToolCards(), match.getMatchDice(), match.getRoundTrack());
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

    //TODO: implementazione
    //Calcolo punteggio di un giocatore
    public PlayerPoints getPlayerPoints(Player player) {
        return null;
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
        return getTurnPlayer().getUser().getName().equals(player.getUser().getName());
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
        if (matchState != MatchState.CHOOSE_WINDOWS)
            throw new MatchException("non puoi scegliere una finestra ora");

        if (!player.getStartWindows().contains(window))
            throw new MatchException("la finestra scelta non è valida");

        //Assegna la finestra
        player.setWindow(window);
        //Assegna i favor tokens
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
        //Controllo stato corretto della partita, della cella e dado scelto
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi piazzare un dado ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        if (!player.getWindow().containsCell(cell))
            throw new MatchException("cella non valida");

        if (!matchDice.getDraftPool().contains(die))
            throw new MatchException("dado non valido");

        //Controlla che il piazzamento rispetti la restrizione del dado iniziale
        if (player.getWindow().isEmpty()) {
            if (!cell.isNorthBorder()&&!cell.isSouthBorder()&&!cell.isWestBorder()&&!cell.isEastBorder())
                throw new MatchException("il primo dado va piazzato vicino ai bordi");
        }

        //Controlla che il piazzamento rispetti la restrizione dei dadi adiacenti
        Cell[] adjacentCells = player.getWindow().getAdjacentCells(cell);
        for (Cell c : adjacentCells) {
            Die placedDie = c.getDie();

            if (die != null) {
                if (die.getColor()==placedDie.getColor() || die.getShade()==placedDie.getShade())
                    throw new MatchException("restrizione dadi adiacenti non rispettata");
            }
        }

        //Posiziona il dado
        cell.placeDie(die);
    }
    //Mossa di utilizzo di una carta strumento
    public void useToolCard(Player player, Match match, ToolCard toolCard) throws RemoteException {
        //Controllo stato corretto della partita e della carta strumento
        if (matchState != MatchState.PLAY_ROUND)
            throw new MatchException("non puoi usare una carta strumento ora");

        if (!isPlayerTurn(player))
            throw new MatchException("non è il tuo turno");

        if (!toolCards.contains(toolCard))
            throw new MatchException("carta strumento non valida");

        //Gestisce tokens player
        if (toolCard.getFavorTokens() == 0)
            if (player.getFavorTokens() < 1)
                throw new MatchException("punti favore insufficenti");
            else
                player.setFavorTokens(player.getFavorTokens()-1);

        if (toolCard.getFavorTokens() >= 1)
            if (player.getFavorTokens() < 2)
                throw new MatchException("punti favore insufficenti");
            else
                player.setFavorTokens(player.getFavorTokens()-2);

        //Utilizza la carta strumento
        toolCard.useToolCard(match, player);
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

            //Se nuovo round sposta draft pool sulla round track e crea nuova draft pool
            if (turnHandler.isRoundFirstTurn()) {
                roundTrack.moveDice(matchDice, turnHandler.getRound()-1);
                matchDice.extractNewDraftPool();
            }
        } else {
            //Aggiorna stato prossimo
            matchState = MatchState.ENDED;
        }
    }
}
