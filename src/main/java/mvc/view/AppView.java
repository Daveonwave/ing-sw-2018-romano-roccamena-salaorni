package mvc.view;

import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.model.objects.*;

import java.io.Serializable;
import java.rmi.RemoteException;

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

    //Operazioni su utente
    public abstract String login(String name) throws RemoteException;
    public abstract void logout() throws RemoteException;

    //Risposta scritta controllore
    public abstract void respondError(String message) throws RemoteException;
    public abstract void respondAck(String message) throws RemoteException;

    //Osservazione partita
    public abstract void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException;
    public abstract void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException;
    public abstract void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException;
    public abstract void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
}
