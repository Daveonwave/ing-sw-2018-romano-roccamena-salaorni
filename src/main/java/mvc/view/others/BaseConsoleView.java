package mvc.view.others;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.view.AppView;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseConsoleView extends AppView {
    //View applicazione come print base della console

    private static final Logger LOGGER = Logger.getLogger(BaseConsoleView.class.getName());

    //Costruttori
    public BaseConsoleView(AppControllerStub appController) {
        super(appController);
    }

    //Risposte controllore
    public void respondError(String message, String tokenMatch) throws RemoteException {
        LOGGER.log(Level.WARNING, message);
    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        LOGGER.log(Level.INFO, message);
    }

    //Osservazione multiplayer
    public void onPlayerLeave(String tokenMatch, Player player) throws RemoteException {
        //Implementazione
    }
    public void onPlayerRejoin(String tokenMatch, Player player) throws RemoteException {
        //Implementazione
    }

    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Implementazione
    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Implementazione
    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Implementazione
    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Implementazione
    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        //Implementazione
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        //Implementazione
    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        //Implementazione
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //Implementazione
    }
}