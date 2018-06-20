package mvc.view;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.io.Serializable;
import java.rmi.RemoteException;

public abstract class AppView implements AppViewStub, Serializable {
    //View astratta dell'applicazione

    private AppControllerStub controller;

    private String userToken;
    private String userName;

    private boolean logged;

    //Costruttori
    public AppView(AppControllerStub controller) {
        this.controller = controller;
        this.userToken = "";
        this.userName = "";
        this.logged = false;
    }

    //Setter/Getter
    public void setController(AppControllerStub controller) {
        this.controller = controller;
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

    public AppControllerStub getController() {
        return controller;
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
    public String login(String name) throws RemoteException {
        String token = controller.login(name, this);

        userToken = token;
        userName = name;
        this.logged = true;

        return token;
    }
    public void logout() throws RemoteException {
        controller.logout(userToken);

        userToken = "";
        userName = "";
        this.logged = false;
    }

    //Risposte controllore
    public abstract void respondError(String message, String tokenMatch) throws RemoteException;
    public abstract void respondAck(String message, String tokenMatch) throws RemoteException;

    //Operazioni multiplayer
    public synchronized void joinMatch() throws RemoteException {
        controller.joinMatch(userToken);
    }
    public synchronized void cancelJoinMatch() throws RemoteException {
        controller.cancelJoinMatch(userToken);
    }

    //Osservazione multiplayer
    public abstract void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException;

    public abstract void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
    public abstract void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException;
    public abstract void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException;
    public abstract void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException;
    public abstract void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException;
}
