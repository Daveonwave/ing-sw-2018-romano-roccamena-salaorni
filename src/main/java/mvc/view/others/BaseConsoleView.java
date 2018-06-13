package mvc.view.others;

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

    //Risposte controllore
    public void respondError(String message, String tokenMatch) throws RemoteException {
        System.out.println("[ERROR] " + message);
    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        System.out.println("[INFO] " + message);
    }

    //Osservazione multiplayer
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }

    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
    }
}