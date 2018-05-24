package mvc;

import mvc.creators.MatchCreator;
import mvc.model.objects.*;
import org.junit.Test;

import java.rmi.RemoteException;

public class MultiPlayerTest extends MVCTest {
    //Test su partite multiplayer del model

    private static final String INVALID_OPERATION_MESSAGE = "invalid match operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid match operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid match state encountered";


    //Creazione partite di test
    private Match createTwoPlayerMatch() {
        return MatchCreator.createMultiPlayer(createTwoUsersList());
    }
    private Match createThreePlayerMatch() {
        return MatchCreator.createMultiPlayer(createThreeUsersList());
    }
    private Match createFourPlayerMatch() {
        return MatchCreator.createMultiPlayer(createFourUsersList());
    }

    private Match createTwoPlayerMatch1() {
        return MatchCreator.createMultiPlayer(createTwoUsersList(), 10);
    }
    private Match createThreePlayerMatch1() {
        return MatchCreator.createMultiPlayer(createThreeUsersList(), 10);
    }
    private Match createFourPlayerMatch1() {
        return MatchCreator.createMultiPlayer(createFourUsersList(), 10);
    }


    //Operazioni di test su partite
    public Window retrieveStartWindow(Player player, int index) {
        return validRetrieveStartWindow(player, player.getStartWindows().get(index));
    }
    public Window validRetrieveStartWindow(Player player, Window window) {
        try {
            return player.retrieveStartWindow(window);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Window invalidRetrieveStartWindow(Player player, Window window) {
        try {
            player.retrieveStartWindow(window);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Cell retrieveCell(Window window, int row, int column) {
        return validRetrieveCell(window, window.getCells()[row][column]);
    }
    public Cell validRetrieveCell(Window window, Cell cell) {
        try {
            return window.retrieveCell(cell);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Cell invalidRetrieveCell(Window window, Cell cell) {
        try {
            window.retrieveCell(cell);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Player retrievePlayer(Match match, int index) {
        return validRetrievePlayer(match, match.getPlayers().get(index));
    }
    public Player validRetrievePlayer(Match match, Player player) {
        try {
            return match.retrievePlayer(player);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Player invalidRetrievePlayer(Match match, Player player) {
        try {
            match.retrievePlayer(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public ToolCard retrieveToolCard(Match match, int index) {
        return validRetrieveToolCard(match, match.getToolCards().get(index));
    }
    public ToolCard validRetrieveToolCard(Match match, ToolCard toolCard) {
        try {
            return match.retrieveToolCard(toolCard);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public ToolCard invalidRetrieveToolCard(Match match, ToolCard toolCard) {
        try {
            match.retrieveToolCard(toolCard);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public Die retrieveDieFromDraftPool(Match match, int index) {
        return validRetrieveDieFromDraftPool(match, match.getMatchDice().getDraftPool().get(index));
    }
    public Die validRetrieveDieFromDraftPool(Match match, Die die) {
        try {
            return match.getMatchDice().retrieveDieFromDraftPool(die);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public Die invalidRetrieveDieFromDraftPool(Match match, Die die) {
        try {
            match.getMatchDice().retrieveDieFromDraftPool(die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public void validBeginMatch(Match match) {
        try {
            match.beginMatch();
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidBeginMatch(Match match) {
        try {
            match.beginMatch();
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validChooseWindow(Match match, Player player, Window window) {
        try {
            match.chooseWindow(player, window);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidChooseWindow(Match match, Player player, Window window) {
        try {
            match.chooseWindow(player, window);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validPlaceDie(Match match, Player player, Cell cell, Die die) {
        try {
            match.placeDie(player, cell, die);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidPlaceDie(Match match, Player player, Cell cell, Die die) {
        try {
            match.placeDie(player, cell, die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void invalidUseToolCard(Match match, Player player, ToolCardInput toolCardInput, ToolCard toolCard) {
        try {
            match.useToolCard(player, toolCardInput, toolCard);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }
    public void validUseToolCard(Match match, Player player, ToolCardInput toolCardInput, ToolCard toolCard) {
        try {
            match.useToolCard(player, toolCardInput, toolCard);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }

    public void validEndTurn(Match match, Player player) {
        try {
            match.endTurn(player);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidEndTurn(Match match, Player player) {
        try {
            match.endTurn(player);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    //Test
    @Test
    public void randomTwoPlayerStart() {
        //Creazione partita
        Match match = createTwoPlayerMatch();

        //Controlla correttezza partita creata
        if (match.getPlayers().size() != 2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchDice().getDiceBag().size() != 85)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchDice().getDraftPool().size() != 5)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getPublicObjectiveCards().size()!=GameConstants.MP_PUBLIC_OBJECTIVES_COUNT)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getToolCards().size()!=GameConstants.MP_TOOL_CARDS_COUNT)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchState()!=MatchState.STARTED)
            testAssertError(INVALID_STATE_MESSAGE);

        //Controlla correttezza giocatori creati
        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        if (!player1.getUser().sameUser(user1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getUser().sameUser(user2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (player1.getStartWindows().size() != 4)
            testAssertError(INVALID_STATE_MESSAGE);
        if (player2.getStartWindows().size() != 4)
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita non iniziata
        invalidChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        invalidPlaceDie(match, player2, createInvalidCell1(), createInvalidDie1());
        invalidEndTurn(match, player1);

        //Inizio partita
        validBeginMatch(match);

        //Controllo stato
        if (match.getMatchState()!=MatchState.CHOOSE_WINDOWS)
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita iniziata
        invalidUseToolCard(match, player2, createInvalidToolCardInput1(), retrieveToolCard(match, 0));
        invalidPlaceDie(match, player1, createInvalidCell1(), createInvalidDie1());
        invalidEndTurn(match, player2);

        //Riinizio scorretto partita
        invalidBeginMatch(match);

        //Scelta finestre corretta
        validChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 0));

        //Controllo correttezza stato
        if (!player1.getWindow().sameWindow(player1.getStartWindows().get(0)))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getWindow().sameWindow(player2.getStartWindows().get(0)))
            testAssertError(INVALID_STATE_MESSAGE);
        if (player1.getFavorTokens()!=player1.getWindow().getDifficulty())
            testAssertError(INVALID_STATE_MESSAGE);
        if (player2.getFavorTokens()!=player2.getWindow().getDifficulty())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchState()!=MatchState.PLAY_ROUND)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnHandler().isFirstTurn())
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnHandler().isFirstTurn())
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnHandler().isFirstTurnWave())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isTurnWaveChanging())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isEnded())
            testAssertError(INVALID_STATE_MESSAGE);
    }
    @Test
    public void twoPlayerRounds1() {
        //Creazione partita
        Match match = createTwoPlayerMatch1();

        Player player1 = retrievePlayer(match, 0);
        Player player2 = retrievePlayer(match, 1);

        //Inizio partita
        validBeginMatch(match);
        validChooseWindow(match, player1, retrieveStartWindow(player1, 0));
        validChooseWindow(match, player2, retrieveStartWindow(player2, 0));

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell;
        Die die;

        MatchDice matchDice = match.getMatchDice();

        //--------------------------------------- ROUND 1 -------------------------------------------//

        /////////////////
        //GIOCA PLAYER1//
        /////////////////

        //Azioni non valide
        invalidEndTurn(match, player2); // NO SUO TURNO
        invalidPlaceDie(match, player1, retrieveCell(window1, 2, 1), retrieveDieFromDraftPool(match, 1)); // NO RESTRIZIONE INIZIALE
        invalidPlaceDie(match, player1, retrieveCell(window1, 2, 0), retrieveDieFromDraftPool(match, 0)); // NO RESTRIZIONE CELLA
        invalidUseToolCard(match, player1, new ToolCardInput(createInvalidDie1()), retrieveToolCard(match, 1));

        //Piazzamento corretto dado
        cell = retrieveCell(window1, 0, 2);
        die = retrieveDieFromDraftPool(match, 1);
        validPlaceDie(match, player1, cell, die);

        //Controllo correttezza piazzamento
        if (matchDice.getDraftPool().size()!=4)
            testAssertError(INVALID_STATE_MESSAGE);
        if (matchDice.containsDieInPool(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (cell.getDie()==null)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!cell.getDie().sameDie(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!window1.containsCell(cell))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player1.getTurnDiePlaced())
            testAssertError(INVALID_STATE_MESSAGE);

        //Dado gia piazzato
        invalidPlaceDie(match, player1, retrieveCell(window1, 0, 3), retrieveDieFromDraftPool(match, 2));

        //Controllo turni
        if (!match.getTurnHandler().isStarted())
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnHandler().isFirstTurnWave())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isLastTurn())
            testAssertError(INVALID_STATE_MESSAGE);

        //Finisce turno
        validEndTurn(match, player1);

        //Controllo stato corretto
        if (match.getTurnHandler().isFirstTurn())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().getTurnPlayerIndex()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().getFirstPlayerIndex()!=0)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnHandler().isFirstTurnWave())
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnPlayer().samePlayer(player2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getFirstPlayer().samePlayer(player1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchState()!=MatchState.PLAY_ROUND)
            testAssertError(INVALID_STATE_MESSAGE);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////

        //Azioni non valide
        invalidEndTurn(match, player1); // NO SUO TURNO
        invalidPlaceDie(match, player2, retrieveCell(window2, 2, 3), retrieveDieFromDraftPool(match, 1)); // NO RESTRIZIONE INIZIALE
        invalidPlaceDie(match, player2, retrieveCell(window2, 3, 2), retrieveDieFromDraftPool(match, 0)); // NO RESTRIZIONE CELLA
        invalidUseToolCard(match, player2, new ToolCardInput(retrieveCell(window2, 0,0), null, retrieveCell(window2, 0,0), null), retrieveToolCard(match, 0)); // NESSUN DADO PIAZZATO
        invalidChooseWindow(match, player2, retrieveStartWindow(player2, 0));

        //Piazzamento corretto dado
        cell = retrieveCell(window2, 2, 4);
        die = retrieveDieFromDraftPool(match, 0);
        validPlaceDie(match, player2, cell, die);

        //Controllo correttezza piazzamento
        if (matchDice.getDraftPool().size()!=3)
            testAssertError(INVALID_STATE_MESSAGE);
        if (matchDice.containsDieInPool(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (cell.getDie()==null)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!cell.getDie().sameDie(die))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!window2.containsCell(cell))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!player2.getTurnDiePlaced())
            testAssertError(INVALID_STATE_MESSAGE);

        //Finisce turno
        validEndTurn(match, player2);

        //Fine test corretto
        testAssertWaitingUpdate();

        //Controllo stato corretto
        if (match.getTurnHandler().isFirstTurn())
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().getTurnPlayerIndex()!=1)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().getFirstPlayerIndex()!=0)
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getTurnHandler().isFirstTurnWave())
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getTurnPlayer().samePlayer(player2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!match.getFirstPlayer().samePlayer(player1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (match.getMatchState()!=MatchState.PLAY_ROUND)
            testAssertError(INVALID_STATE_MESSAGE);

        /////////////////
        //GIOCA PLAYER2//
        /////////////////
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        MultiPlayerTest test = new MultiPlayerTest();

        //Lancia i test
        test.randomTwoPlayerStart();
        test.twoPlayerRounds1();
    }
}

