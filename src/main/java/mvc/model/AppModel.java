package mvc.model;

import mvc.TokenMap;
import mvc.model.objects.*;
import mvc.view.AppView;

import java.rmi.RemoteException;
import java.util.*;

public class AppModel {
    //Model dell'applicazione

    //Istanza del model dell'applicazione
    private static AppModel instance;

    //Utenti online
    private final TokenMap<User> users;
    //Partite online
    private final TokenMap<MatchModel> matches;

    //Costruttori
    private AppModel() {
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
        User user = users.get(name);

        if (user == null)
            throw new ModelException("user named " + name + " already logged in");

        return users.createObject(new User(name, appView, new ArrayList<Player>()));
    }
    public synchronized void destroyUser(String tokenUser) throws RemoteException {
        users.remove(tokenUser);
    }
    public synchronized User retrieveUser(String tokenUser) {
        return users.get(tokenUser);
    }

    //Partita
    public synchronized String createMatch(MatchModel match) {
        return matches.createObject(match);
    }
    public synchronized void destroyMatch(String tokenMatch) throws RemoteException {
        matches.remove(tokenMatch);
    }
    public synchronized MatchModel retrieveMatch(String tokenMatch) {
        return matches.get(tokenMatch);
    }
    public synchronized void modifyMatch(String tokenMatch, MatchModel match) throws RemoteException {
        matches.modifyObject(tokenMatch, match);
    }

    public synchronized Player retrievePlayer(String tokenUser, String tokenMatch) {
        User user = retrieveUser(tokenUser);
        MatchModel match = retrieveMatch(tokenMatch);

        Player player = null;
        for (Player p : match.getPlayers()) {
            if (p.getUser().getName().equals(user.getName())) {
                player = p;
                break;
            }
        }

        return player;
    }

}