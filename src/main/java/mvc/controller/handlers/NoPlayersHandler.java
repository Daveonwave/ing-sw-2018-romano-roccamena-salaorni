package mvc.controller.handlers;

import mvc.controller.AppController;
import mvc.controller.TimedSubcontroller;
import mvc.model.objects.User;
import mvc.stubs.AppControllerStub;

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

            //Comunica fine attesa partita
            controller.userError(user, "nessun giocatore disponibile");
        } else
            controller.startMatch();

        //Crea nuovo timer
        lobby.setNoPlayersHandler(new NoPlayersHandler(controller, controller.TURN_MAX_TIME));
    }
    public synchronized void onTimedTaskException(Exception e) {
        //Non fa nulla
    }
}
