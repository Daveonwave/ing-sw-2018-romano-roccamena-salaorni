package mvc.controller.handlers;

import mvc.controller.AppController;

import java.util.ArrayList;
import java.util.List;

/**
 * Variabile size multiplayer lobby handler of the application controller
 */
public class MultiPlayerHandler {
    //Gestore servizio multigiocatore

    private AppController controller;

    private boolean ready;
    private int usersCount;
    private List<String> waitingUsersToken;

    private NoPlayersHandler noPlayersHandler;

    //Costruttori
    /**
     * Create new handler
     * @param controller Parent controller
     * @param usersCount Size of the lobby
     * @param delay Delay of timer
     */
    public MultiPlayerHandler(AppController controller, int usersCount, int delay) {
        this.ready = false;
        this.usersCount = usersCount;
        this.waitingUsersToken = new ArrayList<>();
        this.controller = controller;
        this.noPlayersHandler = new NoPlayersHandler(controller, delay);
    }

    //Setter/Getter
    public void setNoPlayersHandler(NoPlayersHandler noPlayersHandler) {
        this.noPlayersHandler = noPlayersHandler;
    }

    public AppController getController() {
        return controller;
    }
    public synchronized boolean isReady() {
        return ready;
    }
    public synchronized List<String> getWaitingUsersToken() {
        return waitingUsersToken;
    }
    public NoPlayersHandler getNoPlayersHandler() {
        return noPlayersHandler;
    }

    //Elimina tutti gli utenti dall'attesa
    /**
     * Free waiting users list
     */
    public synchronized void clear() {
        waitingUsersToken.clear();
        ready = false;
    }

    //Ottiene i token degli utenti, eliminandoli dall'attesa

    /**
     * Obtains tokens of waiting users
     * @return
     */
    public synchronized List<String> retrieveWaitingUsersToken() {
        List<String> copyUsers = new ArrayList<>();
        for (String token : waitingUsersToken)
            copyUsers.add(token);

        clear();

        return copyUsers;
    }

    //Registra un'utente in attesa
    /**
     * Register a logged user as a partecipant of the new multiplayer matcch
     * @param tokenUser User's token
     */
    public synchronized void join(String tokenUser) {
        waitingUsersToken.add(tokenUser);

        //Avvia timer di nessun giocatore se ci sono 2 giocatori
        if (waitingUsersToken.size() == 2) {
            noPlayersHandler.start();
        }

        //Controlla se la partita ha inizio
        if (waitingUsersToken.size() == usersCount) {
            ready = true;

            //Termina e resetta timer di nessun giocatore
            noPlayersHandler.stop();
            controller.resetNoPlayerHandler();
        }
        else
            ready = false;
    }
    //Elimina un'utente dall'attesa
    /**
     * Unregister a logged user as a partecipant of the new multiplayer matcch
     * @param tokenUser User's token
     */
    public synchronized void leave(String tokenUser) {
        waitingUsersToken.remove(tokenUser);
    }
}
