package mvc.controller.handlers;

import java.util.ArrayList;
import java.util.List;

public class MultiPlayerHandler {
    //Gestore servizio multiutente

    private boolean ready;
    private int usersCount;
    private List<String> waitingUsersToken;

    //Costruttori
    public MultiPlayerHandler(int usersCount) {
        this.ready = false;
        this.usersCount = usersCount;
        this.waitingUsersToken = new ArrayList<String>();
    }

    //Il gestore ha ottenuto abbastanza utenti
    public synchronized boolean isReady() {
        return ready;
    }
    //Ottiene utenti in attesa
    public synchronized List<String> getWaitingUsersToken() {
        return waitingUsersToken;
    }
    //Registra un'utente in attesa
    public synchronized void join(String tokenUser) {
        waitingUsersToken.add(tokenUser);

        if (waitingUsersToken.size() == usersCount)
            ready = true;
        else
            ready = false;
    }
    //Elimina un'utente dall'attesa
    public synchronized void leave(String tokenUser) {
        waitingUsersToken.remove(tokenUser);
    }
    //Elimina tutti gli utenti dall'attesa
    public synchronized void clear() {
        waitingUsersToken.clear();
    }
}
