package mvc.controller.handlers;

import java.util.*;

public class MultiPlayerHandler {
    //Gestore servizio multigiocatore

    public static final int WAIT_TIME = 2 * 1000;

    private boolean ready;
    private int usersCount;
    private List<String> waitingUsersToken;
    private Timer timer;
    private TimerTask timerTask;

    //Costruttori
    public MultiPlayerHandler(int usersCount, TimerTask timerTask) {
        this.ready = false;
        this.usersCount = usersCount;
        this.waitingUsersToken = new ArrayList<String>();
        this.timer = new Timer();
        this.timerTask = timerTask;
    }

    //Setter/Getter
    public synchronized void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public synchronized boolean isReady() {
        return ready;
    }
    public synchronized List<String> getWaitingUsersToken() {
        return waitingUsersToken;
    }
    public synchronized TimerTask getTimerTask() {
        return timerTask;
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

        if (waitingUsersToken.size() == 1)
            timer.schedule(timerTask, WAIT_TIME);

        if (waitingUsersToken.size() == usersCount) {
            ready = true;
            timer.cancel();
        }
        else
            ready = false;
    }
    //Elimina un'utente dall'attesa
    public synchronized void leave(String tokenUser) {
        waitingUsersToken.remove(tokenUser);
    }
}
