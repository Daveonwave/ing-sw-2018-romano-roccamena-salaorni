package mvc;

import mvc.creators.MatchCreator;
import mvc.model.objects.*;
import mvc.model.objects.toolcards.AlesatorePerLaminaDiRame;
import org.junit.Test;

import java.rmi.RemoteException;

public class MultiPlayerTest extends MVCTest {
    //Test su partite multiplayer del model

    private static final String INVALID_OPERATION_MESSAGE = "invalid match operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid match operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid match state evolution";

    //Crea partite
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

    //Crea altri oggetto
    private Die createInvalidDie1() {
        return new Die(GameConstants.YELLOW, 1);
    }
    private Cell createInvalidCell1() {
        return new Cell(createInvalidDie1(), new NoRestriction(), 2, 2);
    }
    private Window createInvalidWindow1() {
        return new Window(null, 5);
    }
    private ToolCardInput createInvalidToolCardInput1() {
        return new ToolCardInput(null, null, null, null, 0, createInvalidDie1(), null, 1, true);
    }

    //Test
    @Test
    public void randomTwoPlayerStart() {
        //Creazione partita
        Match match = createTwoPlayerMatch();

        //Controlla correttezza creazione
        if (match.getPlayers().size() != 2)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getMatchDice().getDiceBag().size() != 85)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getMatchDice().getDraftPool().size() != 5)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getPublicObjectiveCards().size()!=GameConstants.MP_PUBLIC_OBJECTIVES_COUNT)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getToolCards().size()!=GameConstants.MP_TOOL_CARDS_COUNT)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getMatchState()!=MatchState.STARTED)
            testAssertError(INVALID_STATE_MESSAGE);

        Player player1 = match.getPlayers().get(0);
        Player player2 = match.getPlayers().get(1);

        if (!player1.getUser().sameUser(user1))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!player2.getUser().sameUser(user2))
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (player1.getStartWindows().size() != 4)
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (player2.getStartWindows().size() != 4)
            testAssertError(FAILED_OPERATION_MESSAGE);

        //Azioni non concesse a partita non iniziata
        try {
            match.chooseWindow(player1, player1.getStartWindows().get(0));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.placeDie(player2, createInvalidCell1(), createInvalidDie1());
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.endTurn(player1);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Inizio partita
        try {
            match.beginMatch();
        } catch (RemoteException e) {
            testAssertError("during match begin: " + e.getMessage());
        }

        if (match.getMatchState()!=MatchState.CHOOSE_WINDOWS)
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita iniziata
        try {
            match.useToolCard(player2, createInvalidToolCardInput1(), match.retrieveToolCard(new AlesatorePerLaminaDiRame()));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.placeDie(player1, createInvalidCell1(), createInvalidDie1());
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.endTurn(player2);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Riinizio scorretto partita
        try {
            match.beginMatch();
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Scelta finestre non valide
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(player2.getStartWindows().get(0)));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.chooseWindow(player2, player2.retrieveStartWindow(player1.getStartWindows().get(0)));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Scelta finestre non valide
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(createInvalidWindow1()));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Scelta finestre corretta
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(player1.getStartWindows().get(0)));
            match.chooseWindow(player2, player2.retrieveStartWindow(player2.getStartWindows().get(0)));
        } catch (RemoteException e) {
            testAssertError("during choose windows: " + e.getMessage());
        }
        try {
            player1 = match.retrievePlayer(player1);
            player2 = match.retrievePlayer(player2);
        } catch (RemoteException e) {
            testAssertError("during player retrieve: " + e.getMessage());
        }

        //Controllo correttezza stato
        if (!player1.getWindow().sameWindow(player1.getStartWindows().get(0)))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!player2.getWindow().sameWindow(player2.getStartWindows().get(0)))
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (player1.getFavorTokens()!=player1.getWindow().getDifficulty())
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (player2.getFavorTokens()!=player2.getWindow().getDifficulty())
            testAssertError(FAILED_OPERATION_MESSAGE);

        if (match.getMatchState()!=MatchState.PLAY_ROUND)
            testAssertError(INVALID_STATE_MESSAGE);

        if (!match.getTurnHandler().isFirstTurn())
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!match.getTurnHandler().isFirstTurn())
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!match.getTurnHandler().isFirstTurnWave())
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (match.getTurnHandler().isTurnWaveChanging())
            testAssertError(FAILED_OPERATION_MESSAGE);
    }
    @Test
    public void twoPlayerRounds1() {
        //Creazione partita
        Match match = createTwoPlayerMatch1();

        Player player1 = match.getPlayers().get(0);
        Player player2 = match.getPlayers().get(1);

        //Inizio partita
        try {
            match.beginMatch();
        } catch (RemoteException e) {
            testAssertError("during begin: " + e.getMessage());
        }
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(player1.getStartWindows().get(0)));
            match.chooseWindow(player2, player2.retrieveStartWindow(player2.getStartWindows().get(0)));
        } catch (RemoteException e) {
            testAssertError("during windows choosing: " + e.getMessage());
        }
        try {
            player1 = match.retrievePlayer(player1);
            player2 = match.retrievePlayer(player2);
        } catch (RemoteException e) {
            testAssertError("during player retrieve: " + e.getMessage());
        }

        Window window1 = player1.getWindow();
        Window window2 = player2.getWindow();

        Cell cell = null;
        Window window = null;
        Die die = null;

        MatchDice matchDice = match.getMatchDice();

        //--------------------------------------- ROUND 1 -------------------------------------------//

        //Non il suo turno
        try {
            match.endTurn(player2);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Piazzamenti non vaidi
        try {
            match.placeDie(player1, window1.retrieveCell(createInvalidCell1()), matchDice.retrieveDieFromDraftPool(createInvalidDie1()));
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            cell = window1.retrieveCell(window1.getCells()[0][2]);
            die = matchDice.retrieveDieFromDraftPool(matchDice.getDraftPool().get(0));
        } catch (RemoteException e) {
            testAssertError("during placing die: " + e.getMessage());
        }
        try {
            match.placeDie(player1, cell, die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        MultiPlayerTest test = new MultiPlayerTest();

        //Lancia i test
        test.randomTwoPlayerStart();
    }
}

