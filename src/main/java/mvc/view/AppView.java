package mvc.view;

import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Client-side application view
 */
public abstract class AppView implements AppViewStub, Serializable {
    //View astratta dell'applicazione

    private AppControllerStub controller;

    private String userToken;
    private String userName;

    private boolean logged;

    //Costruttori
    /**
     * Create new application view
     * @param controller Application controller of the view
     */
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

    //Operazioni multiplayer
    /**
     * Join a multiplayer match
     * @throws IOException
     */
    public synchronized void joinMatch() throws RemoteException {
        controller.joinMatch(userToken);
    }
    /**
     * Cancel partecipation of a multiplayer match
     * @throws RemoteException
     */
    public synchronized void cancelJoinMatch() throws RemoteException {
        controller.cancelJoinMatch(userToken);
    }
}
