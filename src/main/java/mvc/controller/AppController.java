package mvc.controller;

import mvc.model.AppModel;
import mvc.model.objects.*;
import mvc.view.ViewResponse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AppController extends UnicastRemoteObject implements Controller {
    //Controllore dell'applicazione

    private transient final AppModel model;
    private final Map<String, ViewResponse> views = new HashMap<String, ViewResponse>();

    //Costruttori
    protected AppController() throws RemoteException {
        super();
        this.model = AppModel.get();
    }

    //Utente
    public String login(String name, ViewResponse view) throws RemoteException {
        String token = model.createUser(name);
        views.put(token, view);

        view.respondAck("logged in as " + name);

        return token;
    }
    public void logout(String token) throws RemoteException {
        model.destroyUser(token);

        ViewResponse view = views.get(token);
        view.respondAck("logged out");

        views.remove(token);
    }

    //Partita
    public String joinMatch(String tokenUser, int playersCount) throws RemoteException {
        return null;
    }
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {

    }
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {

    }
    public void useToolCard(String tokenUser, String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {

    }
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {

    }
}
