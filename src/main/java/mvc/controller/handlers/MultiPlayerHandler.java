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

    //Chiamate da controllore
    public synchronized boolean isReady() {
        return ready;
    }
    public synchronized List<String> getWaitingUsersToken() {
        return waitingUsersToken;
    }
    public synchronized void join(String tokenUser) {
        waitingUsersToken.add(tokenUser);

        if (waitingUsersToken.size() == usersCount)
            ready = true;
        else
            ready = false;
    }
    public synchronized void leave(String tokenUser) {
        waitingUsersToken.remove(tokenUser);
    }
    public synchronized void clear() {
        waitingUsersToken.clear();
    }
}
