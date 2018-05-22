package mvc;

import mvc.controller.AppController;
import mvc.creators.MatchCreator;
import mvc.model.objects.*;
import mvc.model.objects.toolcards.AlesatorePerLaminaDiRame;
import objects.BaseTest;
import objects.EmptyView;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerTest extends BaseTest {
    //Test su partite multiplayer

    private static final String CREATION_MESSAGE = "invalid match creation";
    private static final String INVALID_ACTION_MESSAGE = "invalid match operation accepted";
    private static final String INVALID_STATE_MESSAGE = "invalid match state evolution";
    private static final String FAILED_OPERATION_MESSAGE = "correct match operation failed";

    //Test
    @Test
    public void twoPlayer() {
        //Controllore locale di prova
        AppController controller = new AppController();

        //Creazione partita
        List<User> users = new ArrayList<User>();
        User user1 = new User("jack", new EmptyView(controller));
        User user2 = new User("john", new EmptyView(controller));

        users.add(user1);
        users.add(user2);

        Match match = MatchCreator.createMultiPlayer(users);

        //Controlla correttezza creazione
        if (match.getPlayers().size() != 2)
            testAssertError(CREATION_MESSAGE);

        if (match.getMatchDice().getDiceBag().size() != 85)
            testAssertError(CREATION_MESSAGE);

        if (match.getMatchDice().getDraftPool().size() != 5)
            testAssertError(CREATION_MESSAGE);

        if (match.getPublicObjectiveCards().size()!=GameConstants.MP_PUBLIC_OBJECTIVES_COUNT)
            testAssertError(CREATION_MESSAGE);

        if (match.getToolCards().size()!=GameConstants.MP_TOOL_CARDS_COUNT)
            testAssertError(CREATION_MESSAGE);

        if (match.getMatchState()!=MatchState.STARTED)
            testAssertError(CREATION_MESSAGE);

        Player player1 = match.getPlayers().get(0);
        Player player2 = match.getPlayers().get(1);

        if (!player1.getUser().sameUser(user1))
            testAssertError(CREATION_MESSAGE);
        if (!player2.getUser().sameUser(user2))
            testAssertError(CREATION_MESSAGE);

        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(CREATION_MESSAGE);
        if (player1.getPrivateObjectiveCards().size()!=1)
            testAssertError(CREATION_MESSAGE);

        if (player1.getStartWindows().size() != 4)
            testAssertError(CREATION_MESSAGE);
        if (player2.getStartWindows().size() != 4)
            testAssertError(CREATION_MESSAGE);

        //Azioni non concesse a partita non iniziata
        try {
            match.chooseWindow(player1, player1.getStartWindows().get(0));
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.placeDie(player2, new Cell(null, null, 0, 0), new Die(null, 1));
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.endTurn(player1);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Inizio partita
        try {
            match.beginMatch();
        } catch (RemoteException e) {
            testAssertError("during begin: " + e.getMessage());
        }
        if (match.getMatchState()!=MatchState.CHOOSE_WINDOWS)
            testAssertError(INVALID_STATE_MESSAGE);

        //Azioni non concesse a partita iniziata
        try {
            match.useToolCard(player2, new ToolCardInput(null, null, null, null, 0, null, null, 1, true), new AlesatorePerLaminaDiRame());
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.placeDie(player1, new Cell(null, null, 0, 0), new Die(null, 1));
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.endTurn(player2);
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Riinizio scorretto partita
        try {
            match.beginMatch();

            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Scelta finestre non valide
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(player2.getStartWindows().get(0)));
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }
        try {
            match.chooseWindow(player2, player2.retrieveStartWindow(player1.getStartWindows().get(0)));
            testAssertError(INVALID_ACTION_MESSAGE);
        } catch (RemoteException e) {
        }

        //Scelta finestre malformate
        try {
            match.chooseWindow(player1, player1.retrieveStartWindow(new Window(null, 3)));
            testAssertError(INVALID_ACTION_MESSAGE);
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

        if (!player1.getWindow().sameWindow(player1.getStartWindows().get(0)))
            testAssertError(FAILED_OPERATION_MESSAGE);
        if (!player2.getWindow().sameWindow(player2.getStartWindows().get(0)))
            testAssertError(FAILED_OPERATION_MESSAGE);
    }

    public static void main(String[] args) throws RemoteException {
        //Crea un test
        MultiPlayerTest test = new MultiPlayerTest();

        //Lancia i test
        test.twoPlayer();
    }
}

