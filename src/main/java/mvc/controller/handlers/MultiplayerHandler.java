package mvc.controller.handlers;

import mvc.controller.AppController;

import java.util.ArrayList;
import java.util.List;

public class MultiplayerHandler {
    //Gestore servizio multigiocatore

    private AppController controller;

    private boolean ready;
    private int usersCount;
    private List<String> waitingUsersToken;

    private NoPlayersHandler noPlayersHandler;

    //Costruttori
    public MultiplayerHandler(AppController controller, int usersCount, int mpDelay) {
        this.ready = false;
        this.usersCount = usersCount;
        this.waitingUsersToken = new ArrayList<String>();
        this.controller = controller;
        this.noPlayersHandler = new NoPlayersHandler(controller, mpDelay);
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
    public synchronized void clear() {
        waitingUsersToken.clear();
        ready = false;
    }

    //Ottiene i token degli utenti, eliminandoli dall'attesa
    public synchronized List<String> retrieveWaitingUsersToken() {
        List<String> copyUsers = new ArrayList<String>();
        for (String token : waitingUsersToken)
            copyUsers.add(token);

        clear();

        return copyUsers;
    }

    //Registra un'utente in attesa
    public synchronized void join(String tokenUser) {
        waitingUsersToken.add(tokenUser);

        //Avvia timer di nessun giocatore se ci sono 2 giocatori
        if (waitingUsersToken.size() == 2)
            noPlayersHandler.start();

        //Controlla se la partita ha inizio
        if (waitingUsersToken.size() == usersCount) {
            ready = true;

            //Termina timer di nessun giocatore
            noPlayersHandler.stop();
            noPlayersHandler = new NoPlayersHandler(controller, controller.TURN_MAX_TIME);
        }
        else
            ready = false;
    }
    //Elimina un'utente dall'attesa
    public synchronized void leave(String tokenUser) {
        waitingUsersToken.remove(tokenUser);
    }
}
