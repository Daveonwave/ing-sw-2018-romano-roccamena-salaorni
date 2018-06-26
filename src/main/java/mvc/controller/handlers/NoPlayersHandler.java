package mvc.controller.handlers;

import mvc.controller.AppController;
import mvc.controller.TimedSubcontroller;
import mvc.exceptions.AppControllerException;
import mvc.model.objects.User;

public class NoPlayersHandler extends TimedSubcontroller {
    //Gestore evento di nessun giocatore disponibile per una partita multiplayer

    //Costruttori
    public NoPlayersHandler(AppController controller, int delay) {
        super(controller, delay);
    }

    //Eventi task
    public synchronized void onTimedTask() throws Exception {
        AppController controller = getController();
        MultiplayerHandler lobby = controller.getMultiPlayerLobby();

        //Controlla se non ci sono giocatori sufficienti
        if (lobby.getWaitingUsersToken().size() == 1) {
            //Ottiene dati utente in attesa
            String userToken = lobby.retrieveWaitingUsersToken().get(0);
            User user = null;
            user = controller.getModel().retrieveUser(userToken);

            //Pulisce la lista di attesa della lobby
            lobby.clear();

            //Comunica fine attesa partita
            try {
                controller.userError(user, "nessun giocatore disponibile");
            } catch (AppControllerException e) { }
        } else {
            if (lobby.getWaitingUsersToken().size() != 0) {
                controller.startMatch();
            }
        }

        //Resetta timer di nessun giocatore del controllore
        controller.resetNoPlayerHandler();
    }
    public synchronized void onTimedTaskException(Exception e) {
        //Non fa nulla
    }
}
