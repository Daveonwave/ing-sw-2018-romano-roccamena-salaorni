package mvc.view;

import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.model.objects.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class AppView extends UnicastRemoteObject implements AppViewStub {
    //View astratta dell'applicazione

    private AppControllerStub appController;

    //Costruttori
    protected AppView() throws RemoteException {
        super();
    }
    public AppView(AppControllerStub appController) throws RemoteException {
        super();
        this.appController = appController;
    }

    //Setter/Getter
    public void setAppController(AppControllerStub appController) {
        this.appController = appController;
    }

    public AppControllerStub getAppController() {
        return appController;
    }

    //Risposta scritta controllore
    public abstract void respondError(String message) throws RemoteException;
    public abstract void respondAck(String message) throws RemoteException;

    //Osservazione partita
    public abstract void onMatchStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onChooseWindows(String tokenMatch, Match match) throws RemoteException;
    public abstract void onTurnStart(String tokenMatch, Match match) throws RemoteException;
    public abstract void onTurnEnd(String tokenMatch, Match match) throws RemoteException;
    public abstract void onPlaceDie(String tokenMatch, Match match, Cell cell, Die die) throws RemoteException;
    public abstract void onUseTool(String tokenMatch, Match match, ToolCard toolCard) throws RemoteException;
    public abstract void onGetPoints(String tokenMatch, Match match, Player player, PlayerPoints points) throws RemoteException;
    public abstract void onMatchEnd(String tokenMatch, Match match) throws RemoteException;
}
