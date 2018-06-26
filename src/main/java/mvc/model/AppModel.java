package mvc.model;

import mvc.exceptions.AppModelException;
import mvc.model.objects.Player;
import mvc.model.objects.User;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

/**
 * Server-side application model
 */
public class AppModel {
    //Model dell'applicazione

    //Istanza del model dell'applicazione
    private static AppModel instance;

    //Nomi utenti online
    public final TokenMap<String> names;
    //Utenti online
    public final TokenMap<User> users;
    //Partite online
    public final TokenMap<MatchModel> matches;

    //Costruttori
    /**
     * Create new application model instance
     */
    private AppModel() {
        this.names = new TokenMap<String>();
        this.users = new TokenMap<User>();
        this.matches = new TokenMap<MatchModel>();
    }

    //Getter singleton

    /**
     * Retrieve application model instance
     * @return
     */
    public synchronized static AppModel get() {
        if (instance == null) {
            instance = new AppModel();
        }

        return instance;
    }

    //Utente
    public synchronized String createUser(String name, AppViewStub appView) throws RemoteException {
        //Controllo correttezza
        if (name == null || appView == null)
            throw new AppModelException("parametri non validi");

        if (names.values().contains(name))
            throw new AppModelException("l'utente " + name + " è gia connesso");

        //Crea utente
        String token = users.createObject(new User(name, appView));
        names.put(token, name);
        return token;
    }
    public synchronized void destroyUser(String tokenUser) throws RemoteException {
        //Controllo correttezza
        if (tokenUser == null)
            throw new AppModelException("parametri non validi");

        //Distrugge utente
        names.destroyObject(tokenUser);
        users.destroyObject(tokenUser);
    }
    public synchronized User retrieveUser(String tokenUser) throws RemoteException {
        //Controllo correttezza
        if (tokenUser == null)
            throw new AppModelException("parametri non validi");

        //Ottiene utente
        User user = users.get(tokenUser);

        if (user == null)
            throw new AppModelException("utente non trovato");

        return user;
    }

    //Partita
    public synchronized String createMatch(MatchModel match) throws RemoteException {
        //Controlla correttezza
        if (match==null)
            throw new AppModelException("partita non valida");

        //Crea partita
        return matches.createObject(match);
    }
    public synchronized void destroyMatch(String tokenMatch) throws RemoteException {
        //Controlla correttezza
        if (tokenMatch==null)
            throw new AppModelException("token non valido");

        //Distrugge oggetto
        matches.destroyObject(tokenMatch);
    }
    public synchronized MatchModel retrieveMatchModel(String tokenMatch) throws RemoteException {
        //Controlla correttezza
        if (tokenMatch==null)
            throw new AppModelException("token non valido");

        //Ottiene oggetto
        MatchModel match = matches.get(tokenMatch);
        if (match == null)
            throw new AppModelException("partita non trovata");

        return match;
    }

    public synchronized Player retrievePlayer(String tokenUser, String tokenMatch) throws RemoteException {
        //Controlla correttezza
        if (tokenUser==null || tokenMatch==null)
            throw new AppModelException("token non validi");

        //Ottiene giocatore
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