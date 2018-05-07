package mvc.view;

import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.model.objects.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class AppView implements AppViewStub, Serializable {
    //View astratta dell'applicazione

    private AppControllerStub appController;
    private String userToken;
    private String userName;
    private boolean logged;

    //Costruttori
    public AppView(AppControllerStub appController) {
        this.appController = appController;
        this.userToken = "";
        this.userName = "";
        this.logged = false;
    }

    //Setter/Getter
    public void setAppController(AppControllerStub appController) {
        this.appController = appController;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public AppControllerStub getAppController() {
        return appController;
    }
    public String getUserToken() {
        return userToken;
    }
    public String getUserName() {
        return userName;
    }
    public boolean isLogged() {
        return logged;
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
