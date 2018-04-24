package mvc.model;


import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.User;

import java.util.*;

public class AppModel {
    //Model dell'applicazione

    private static AppModel instance;

    //Utenti e partite online
    private final TokenMap<User> users;
    private final TokenMap<Match> matches;

    //Costruttori
    private AppModel() {
        users = new TokenMap<User>();
        matches = new TokenMap<Match>();
    }

    //Getter statico
    public synchronized static AppModel get() {
        if (instance == null) {
            instance = new AppModel();
        }

        return instance;
    }

    //Utente
    public synchronized String createUser(String name) throws ModelException {
        User user = users.get(name);

        if (user == null)
            throw new ModelException("user named " + name + " already logged in");

        return users.createObject(new User(name, new ArrayList<Player>()));
    }
    public synchronized void destroyUser(String tokenUser) throws ModelException {
        users.remove(tokenUser);
    }
    public synchronized User retrieveUser(String name) {
        return users.get(name);
    }

    //Partita
    public synchronized String createMatch(Match match) {
        return matches.createObject(match);
    }
    public synchronized void destroyMatch(String tokenMatch) throws ModelException {
        matches.remove(tokenMatch);
    }
    public synchronized Match retrieveMatch(String tokenMatch) {
        return matches.get(tokenMatch);
    }
}