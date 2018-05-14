package match;

import mvc.controller.AppController;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class MultiPlayerTest extends AppView {

    //Costruttore
    public MultiPlayerTest() throws RemoteException {
        super(new AppController());
    }

    //Operazioni su utente
    public String login(String name) throws RemoteException {
        return null;
    }
    public void logout() throws RemoteException {

    }

    //Risposta al controllore
    public void respondError(String message) throws RemoteException {
        System.out.println("[ERROR] " + message);
    }
    public void respondAck(String message) throws RemoteException {
        System.out.println("[INFO] " + message);
    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, Match match) throws RemoteException {
    }
    public void onChooseWindows(String tokenMatch, Match match) throws RemoteException {

    }
    public void onTurnStart(String tokenMatch, Match match) throws RemoteException {
    }
    public void onTurnEnd(String tokenMatch, Match match) throws RemoteException {
    }
    public void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException {
    }
    public void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {
    }
    public void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException {
    }
    public void onMatchEnd(String tokenMatch, Match match) throws RemoteException {
    }

    //Test
    @Test
    public void multiPlayerTest() {

    }

    public static void main(String[] args) throws RemoteException {
        MultiPlayerTest test = new MultiPlayerTest();

        //Controllore di prova
        AppControllerStub controller = test.getAppController();

        //Dati partecipanti
        String name1 = "jack";
        String name2 = "john";

        String token1 = "";
        String token2 = "";

        //Login
        try {
            token1 = controller.login(name1, test);
            token2 = controller.login(name2, test);
        } catch (RemoteException e) {
            assertTrue("during login: " + e.getMessage(), true);
        }

        //Logut a caso
        try {
            controller.logout("ciao");

            assertTrue("random logout accepted without errors", true);
        } catch (RemoteException e) {
        }

        //Login con stessi nomi
        try {
            controller.login(name1, test);
            controller.login(name2, test);

            assertTrue("multiple name login accepted without errors", true);
        } catch (RemoteException e) {
        }

        //Logut e login di nuovo
        try {
            controller.logout(token1);
            controller.logout(token2);
            token1 = controller.login(name1, test);
            token2 = controller.login(name2, test);
        } catch (RemoteException e) {
            assertTrue("during logout and relogin: " + e.getMessage(), true);
        }

        //Partecipa partita
        try {
            controller.joinMatch(token1, 2);
            controller.joinMatch(token2, 2);
        } catch (RemoteException e) {
            assertTrue("during join match: " + e.getMessage(), true);
        }
    }
}

