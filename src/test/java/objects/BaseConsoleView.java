package objects;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.rmi.RemoteException;

public class BaseConsoleView extends AppView {
    //View applicazione come print base della console

    //Costruttori
    public BaseConsoleView(AppControllerStub appController) {
        super(appController);
    }

    //Utente
    public String login(String name) throws RemoteException {
        return null;
    }
    public void logout() throws RemoteException {

    }

    //Risposte controllore
    public void respondError(String message) throws RemoteException {

    }
    public void respondAck(String message) throws RemoteException {

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
}