package mvc;

import mvc.controller.AppController;
import mvc.exceptions.MatchException;
import mvc.model.objects.*;
import mvc.stubs.AppViewStub;
import mvc.view.others.EmptyView;
import org.junit.Test;

import java.rmi.RemoteException;

public class ControllerTest extends MVCTest {
    //Test sul controllore mvc

    private static final String INVALID_OPERATION_MESSAGE = "invalid user operation accepted";
    private static final String FAILED_OPERATION_MESSAGE = "valid user operation failed";
    private static final String INVALID_STATE_MESSAGE = "invalid user state encountered";

    protected AppController controller;

    protected String token1 = "";
    protected String token2 = "";
    protected String token3 = "";
    protected String token4 = "";

    protected String tokenMatch = "";




    //Operazioni di test su utenti
    public String validLogin(String name, AppViewStub appView) {
        try {
            return controller.login(name, appView);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }

        return null;
    }
    public String invalidLogin(String name, AppViewStub appView) {
        try {
            controller.login(name, appView);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }

        return null;
    }

    public void validLogout(String token) {
        try {
            controller.logout(token);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidLogout(String token) {
        try {
            controller.logout(token);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }




    //Operazioni di test su partite
    public void validJoinMatch(String tokenUser) {
        try {
            controller.joinMatch(tokenUser);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidJoinMatch(String tokenUser) {
        try {
            controller.joinMatch(tokenUser);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validLeaveMatch(String tokenUser, String tokenMatch) {
        try {
            controller.leaveMatch(tokenUser, tokenMatch);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidLeaveMatch(String tokenUser, String tokenMatch) {
        try {
            controller.leaveMatch(tokenUser, tokenMatch);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validRejoinMatch(String tokenUser, String tokenMatch) {
        try {
            controller.rejoinMatch(tokenUser, tokenMatch);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidRejoinMatch(String tokenUser, String tokenMatch) {
        try {
            controller.rejoinMatch(tokenUser, tokenMatch);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validChooseWindow(String tokenUser, String tokenMatch, Window window) {
        try {
            controller.chooseWindow(tokenUser, tokenMatch, window);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidChooseWindow(String tokenUser, String tokenMatch, Window window) {
        try {
            controller.chooseWindow(tokenUser, tokenMatch, window);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validPlaceDie(String tokenUser, String tokenMatch, Cell cell, Die die) {
        try {
            controller.placeDie(tokenUser, tokenMatch, cell, die);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidPlaceDie(String tokenUser, String tokenMatch, Cell cell, Die die) {
        try {
            controller.placeDie(tokenUser, tokenMatch, cell, die);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validUseToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) {
        try {
            controller.useToolCard(tokenUser, tokenMatch, input, toolCard);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidUseToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) {
        try {
            controller.useToolCard(tokenUser, tokenMatch, input, toolCard);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }

    public void validEndTurn(String tokenUser, String tokenMatch) {
        try {
            controller.endTurn(tokenUser, tokenMatch);
        } catch (RemoteException e) {
            testAssertError(FAILED_OPERATION_MESSAGE);
        }
    }
    public void invalidEndTurn(String tokenUser, String tokenMatch) {
        try {
            controller.endTurn(tokenUser, tokenMatch);
            testAssertError(INVALID_OPERATION_MESSAGE);
        } catch (RemoteException e) {
        }
    }





    //Test su proprietà partita multiplayer
    public void testPlayerTurn(MultiPlayerMatch match, Player player) {
        if (!match.getTurnPlayer().samePlayer(player))
            testAssertError(INVALID_STATE_MESSAGE);
    }

    //Test sulle azioni dei giocatori su partite multiplayer
    public void testPlayerLeave(Player player) {
        if (player.isActive())
            testAssertError(INVALID_STATE_MESSAGE);
    }
    public void testPlayerRejoin(Player player) {
        if (!player.isActive())
            testAssertError(INVALID_STATE_MESSAGE);
    }





    //Test su utenti
    @Test
    public synchronized void userControl() {
        //Crea view di test con controllore locale di prova
        this.controller = createTestController();
        AppViewStub view = new EmptyView(controller);

        //Login validi
        token1 = validLogin(user1.getName(), view);
        token2 = validLogin(user2.getName(), view);

        //Controllo stato registrazione
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logut a caso
        invalidLogout("ciao");

        //Controllo stato corretto
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Login con stessi nomi
        invalidLogin(user1.getName(), view);
        invalidLogin(user2.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logut e login di nuovo
        validLogout(token1);
        validLogout(token2);

        token1 = validLogin(user1.getName(), view);
        token2 = validLogin(user2.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=2)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);

        //Logout e login malformati
        invalidLogin(null, view);
        invalidLogin("", null);
        invalidLogin(null, null);
        invalidLogin(null, view);
        invalidLogout(null);

        //Nuovi login
        token3 = validLogin(user3.getName(), view);
        token4 = validLogin(user4.getName(), view);

        //Controllo correttezza
        if (controller.getModel().users.size()!=4)
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token1))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token2))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token3))
            testAssertError(INVALID_STATE_MESSAGE);
        if (!controller.getModel().users.containsKey(token4))
            testAssertError(INVALID_STATE_MESSAGE);
    }

    //Test su partite
    @Test
    public void randomFourPlayerActivityTest() {
        //Inizializzazione test
        controller = createTestController();
        AppViewStub view = new EmptyView(controller);

        createFourUsersList();

        token1 = validLogin(user1.getName(), view);
        token2 = validLogin(user2.getName(), view);
        token3 = validLogin(user3.getName(), view);
        token4 = validLogin(user4.getName(), view);

        validJoinMatch(token1);
        validJoinMatch(token2);
        validJoinMatch(token3);
        validJoinMatch(token4);

        if (controller.getModel().matches.size()!=1)
            testAssertError(INVALID_STATE_MESSAGE);

        for (String token : controller.getModel().matches.keySet())
            tokenMatch = token;

        MultiPlayerMatch match = controller.getModel().matches.get(tokenMatch).getMatch();

        Player player1 = match.getPlayers().get(0);
        Player player2 = match.getPlayers().get(1);
        Player player3 = match.getPlayers().get(2);
        Player player4 = match.getPlayers().get(3);

        validChooseWindow(token1, tokenMatch, player1.getStartWindows().get(0));
        validChooseWindow(token2, tokenMatch, player2.getStartWindows().get(0));
        validChooseWindow(token3, tokenMatch, player3.getStartWindows().get(0));
        validChooseWindow(token4, tokenMatch, player4.getStartWindows().get(0));

        //--------------------------------------- ROUND 1 -------------------------------------------//

        //Secondo abbandona
        validLeaveMatch(token2, tokenMatch);
        testPlayerLeave(player2);

        //Primo termina turno
        validEndTurn(token1, tokenMatch);

        //Controlla correttezza sviluppo turni
        testPlayerLeave(player2);
        testPlayerTurn(match, player3);

        //Quarto abbandona
        validLeaveMatch(token4, tokenMatch);
        testPlayerLeave(player4);

        //Terzo termina turno
        validEndTurn(token3, tokenMatch);

        //Secondo prova a riabbandonare
        invalidLeaveMatch(token2, tokenMatch);

        //Controlla correttezza sviluppo turni
        testPlayerLeave(player2);
        testPlayerLeave(player4);
        testPlayerTurn(match, player3);

        //Terzo termina turno
        validEndTurn(token3, tokenMatch);

        //Primo prova a ripartecipare
        invalidRejoinMatch(token1, tokenMatch);

        //Controlla correttezza sviluppo turni
        testPlayerLeave(player2);
        testPlayerLeave(player4);
        testPlayerTurn(match, player1);

        //Primo termina turno
        validEndTurn(token1, tokenMatch);

        //Quarto ripartecipa
        validRejoinMatch(token4, tokenMatch);

        //--------------------------------------- ROUND 2 -------------------------------------------//

        //Round normale
        validEndTurn(token3, tokenMatch);
        validEndTurn(token4, tokenMatch);
        validEndTurn(token1, tokenMatch);
        validEndTurn(token1, tokenMatch);
        validEndTurn(token4, tokenMatch);

        //Secondo ripartecipa
        validRejoinMatch(token2, tokenMatch);

        validEndTurn(token3, tokenMatch);
        validEndTurn(token2, tokenMatch);
    }





    public static void main(String[] args) throws RemoteException {
        //Crea un test
        ControllerTest test = new ControllerTest();

        //Lancia i test
        test.userControl();
        System.out.println("user control passed");
        test.randomFourPlayerActivityTest();
        System.out.println("random four player activity test passed");

        //Termina
        System.exit(0);
    }
}