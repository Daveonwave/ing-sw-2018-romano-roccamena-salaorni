package mvc.model;

import mvc.model.objects.Match;
import mvc.model.objects.Player;
import mvc.model.objects.User;

import javax.jws.soap.SOAPBinding;
import java.rmi.RemoteException;
import java.util.*;

public class AppModel {
    //Model dell'applicazione

    private static AppModel instance;

    //Utenti e partite online
    private final Map<String, User> users;
    private final Map<String, Match> matches;

    //Costruttori
    private AppModel() {
        users = new HashMap<String, User>();
        matches = new HashMap<String, Match>();
    }

    //Getter
    public synchronized static AppModel get() {
        if (instance == null) {
            instance = new AppModel();
        }

        return instance;
    }

    //Crea token libero
    private String getFreeToken(Map map) {
        String token;
        boolean invalid;

        do {
            token = UUID.randomUUID().toString();
            invalid = false;

            for (Object t : map.keySet()) {
                if (t.equals(token)) {
                    invalid = true;
                    break;
                }
            }
        } while(invalid);

        return token;
    }

    //Login
    public synchronized String createUser(String name) throws RemoteException {
        for (User user : users.values()) {
            if (user.getName().equals(name))
                throw new RemoteException("user named " + name + " already logged in");
        }

        String token = getFreeToken(users);
        users.put(token, new User(name, new ArrayList<Player>()));

        return token;
    }
    public synchronized void destroyUser(String tokenUser) throws RemoteException {
        boolean found = false;

       for (String t : users.keySet()) {
           if (t.equals(tokenUser)) {
               users.remove(t);
               found = true;
               break;
           }
       }

       if (!found)
           throw new RemoteException("unknown user token " + tokenUser);
    }

    public synchronized String createMatch(Match match) throws RemoteException {
        String token = getFreeToken(matches);
        matches.put(token, match);

        return token;
    }
    public synchronized void destroyMatch(String tokenMatch) throws RemoteException {
        boolean found = false;

        for (String t : matches.keySet()) {
            if (t.equals(tokenMatch)) {
                matches.remove(t);
                found = true;
                break;
            }
        }

        if (!found)
            throw new RemoteException("unknown match token " + tokenMatch);
    }

}