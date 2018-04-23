package mvc.controller;

import mvc.model.AppModel;
import mvc.model.objects.*;
import mvc.view.ViewResponder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AppController extends UnicastRemoteObject implements Controller {
    //Controllore dell'applicazione

    private transient final AppModel model;
    private final Map<String, ViewResponder> views = new HashMap<String, ViewResponder>();

    //Costruttori
    protected AppController() throws RemoteException {
        super();
        this.model = AppModel.get();
    }

    //Utente
    public synchronized String login(String name, ViewResponder view) throws RemoteException {
        String token = model.createUser(name);
        views.put(token, view);

        view.respondAck("logged in as " + name);

        return token;
    }
    public synchronized void logout(String token) throws RemoteException {
        model.destroyUser(token);

        ViewResponder view = views.get(token);
        view.respondAck("logged out");

        views.remove(token);
    }

    //Partita
    public synchronized String joinMatch(String tokenUser, int playersCount) throws RemoteException {
        return null;
    }
    public synchronized void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }
    public synchronized void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {

    }
    public synchronized void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {

    }
    public synchronized void useToolCard(String tokenUser, String tokenMatch, Match match, ToolCard toolCard) throws RemoteException {
        match.getToolCards().get(0).useToolCard(match);

    }
    public synchronized void endTurn(String tokenUser, String tokenMatch) throws RemoteException {

    }
}
