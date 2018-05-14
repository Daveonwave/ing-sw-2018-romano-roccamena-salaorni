package mvc.model;

import mvc.exceptions.AppModelException;
import mvc.model.objects.*;
import mvc.view.AppView;

import java.rmi.RemoteException;
import java.util.*;

public class AppModel {
    //Model dell'applicazione

    //Istanza del model dell'applicazione
    private static AppModel instance;

    //Nomi utenti online
    private final TokenMap<String> names;
    //Utenti online
    private final TokenMap<User> users;
    //Partite online
    private final TokenMap<MatchModel> matches;

    //Costruttori
    private AppModel() {
        this.names = new TokenMap<String>();
        this.users = new TokenMap<User>();
        this.matches = new TokenMap<MatchModel>();
    }

    //Getter statico
    public synchronized static AppModel get() {
        if (instance == null) {
            instance = new AppModel();
        }

        return instance;
    }

    //Utente
    public synchronized String createUser(String name, AppView appView) throws RemoteException {
        if (names.values().contains(name))
            throw new AppModelException("l'utente " + name + " è gia connesso");

        String token = users.createObject(new User(name, appView, new ArrayList<Player>()));
        names.put(token, name);
        return token;
    }
    public synchronized void destroyUser(String tokenUser) throws RemoteException {
        names.destroyObject(tokenUser);
        users.destroyObject(tokenUser);
    }
    public synchronized User retrieveUser(String tokenUser) throws RemoteException {
        User user = users.get(tokenUser);
        if (user == null)
            throw new AppModelException("utente non trovato");

        return user;
    }

    //Partita
    public synchronized String createMatch(MatchModel match) {
        return matches.createObject(match);
    }
    public synchronized void destroyMatch(String tokenMatch) throws RemoteException {
        matches.destroyObject(tokenMatch);
    }
    public synchronized MatchModel retrieveMatchModel(String tokenMatch) throws AppModelException {
        MatchModel match = matches.get(tokenMatch);
        if (match == null)
            throw new AppModelException("partita non trovata");

        return match;
    }

    public synchronized Player retrievePlayer(String tokenUser, String tokenMatch) throws RemoteException {
        User user = retrieveUser(tokenUser);
        MatchModel match = retrieveMatchModel(tokenMatch);

        Player player = null;
        for (Player p : match.getMatch().getPlayers()) {
            if (p.getUser().getName().equals(user.getName())) {
                player = p;
                break;
            }
        }
        if (player == null)
            throw new AppModelException("giocatore non trovato");

        return player;
    }

}