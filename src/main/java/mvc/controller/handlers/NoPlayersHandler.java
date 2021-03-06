package mvc.controller.handlers;

import mvc.controller.AppController;
import mvc.controller.TimedSubcontroller;
import mvc.creators.MatchCreator;
import mvc.exceptions.AppControllerException;
import mvc.model.objects.User;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Timed subcontroller of no players available feature
 */
public class NoPlayersHandler extends TimedSubcontroller {

    private static final Logger LOGGER = Logger.getLogger(MatchCreator.class.getName());

    //Gestore evento di nessun giocatore disponibile per una partita multiplayer

    //Costruttori
    /**
     * Create new handler
     * @param controller Parent controller
     * @param delay Delay of timer
     */
    public NoPlayersHandler(AppController controller, int delay) {
        super(controller, delay);
    }

    //Eventi task
    /**
     * Decide if a new match is created or not enough players are available for new multiplayers
     * @throws Exception Internal exception
     */
    public synchronized void onTimedTask() throws Exception {
        AppController controller = getController();
        MultiPlayerHandler lobby = controller.getMultiPlayerLobby();

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
                controller.userError(null,user, "nessun giocatore disponibile");
            } catch (AppControllerException e) {
                LOGGER.log(Level.WARNING,e.getMessage());
            }
        } else {
            if (!lobby.getWaitingUsersToken().isEmpty()) {
                controller.startMatch();
            }
        }

        //Resetta timer di nessun giocatore del controllore
        controller.resetNoPlayerHandler();
    }
    /**
     * No exception handling are used
     * @param e Exception raised
     */
    public synchronized void onTimedTaskException(Exception e) {
        //Non fa nulla
    }
}
