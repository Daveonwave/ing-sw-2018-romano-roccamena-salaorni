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

    private static final String INVALID_PARAMS = "parametri non validi";

    //Costruttori
    /**
     * Create new application model instance
     */
    private AppModel() {
        this.names = new TokenMap<>();
        this.users = new TokenMap<>();
        this.matches = new TokenMap<>();
    }

    //Getter singleton
    /**
     * Retrieve application model instance
     * @return
     */
    public static synchronized AppModel get() {
        if (instance == null) {
            instance = new AppModel();
        }

        return instance;
    }

    //Reset
    /**
     * Reset application model state
     */
    public synchronized void resetAppModel() {
        names.clear();
        users.clear();
        matches.clear();
    }

    //Utente
    /**
     * Register new online user
     * @param name User's name
     * @param appView User's application view
     * @return
     * @throws RemoteException AppModelException if invalid action requested
     */
    public synchronized String createUser(String name, AppViewStub appView) throws RemoteException {
        //Controllo correttezza
        if (name == null || name.equals("") ||appView == null)
            throw new AppModelException(INVALID_PARAMS);

        if (names.values().contains(name))
            throw new AppModelException("l'utente " + name + " è gia connesso");

        //Crea utente
        String token = users.createObject(new User(name, appView));
        names.put(token, name);
        return token;
    }
    /**
     * Unregister an online user
     * @param tokenUser User's token
     * @throws RemoteException AppModelException if invalid action requested
     */
    public synchronized void destroyUser(String tokenUser) throws RemoteException {
        //Controllo correttezza
        if (tokenUser == null)
            throw new AppModelException(INVALID_PARAMS);

        //Distrugge utente
        names.destroyObject(tokenUser);
        users.destroyObject(tokenUser);
    }
    /**
     * Retrieve server user instance by user's token
     * @param tokenUser User's token
     * @return
     * @throws RemoteException AppModelException if invalid action requested
     */
    public synchronized User retrieveUser(String tokenUser) throws RemoteException {
        //Controllo correttezza
        if (tokenUser == null)
            throw new AppModelException(INVALID_PARAMS);

        //Ottiene utente
        User user = users.get(tokenUser);

        if (user == null)
            throw new AppModelException("utente non trovato");

        return user;
    }

    //Partita
    /**
     * Register new online multiplayer match
     * @param match Match instance
     * @return
     * @throws RemoteException AppModelException if invalid action requested
     */
    public synchronized String createMatch(MatchModel match) throws RemoteException {
        //Controlla correttezza
        if (match==null)
            throw new AppModelException("partita non valida");

        //Crea partita
        return matches.createObject(match);
    }
    /**
     * Unregister an online multiplayer match
     * @param tokenMatch Token of the match
     * @throws RemoteException AppModelException if invalid action requested
     */
    public synchronized void destroyMatch(String tokenMatch) throws RemoteException {
        //Controlla correttezza
        if (tokenMatch==null)
            throw new AppModelException("token non valido");

        //Distrugge oggetto
        matches.destroyObject(tokenMatch);
    }

    /**
     * Retrieve server match model instance by its token
     * @param tokenMatch Token of the match
     * @return
     * @throws RemoteException AppModelException if invalid action requested
     */
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

    /**
     * Retrieve an online player
     * @param tokenUser User's token
     * @param tokenMatch Token of the match
     * @return
     * @throws RemoteException AppModelException if invalid action requested
     */
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