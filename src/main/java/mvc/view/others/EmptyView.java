package mvc.view.others;

import mvc.controller.AppController;
import mvc.model.objects.*;
import mvc.view.AppView;

import java.rmi.RemoteException;

public class EmptyView extends AppView {
    //View applicazione vuota

    //Costruttori
    public EmptyView(AppController appController) {
        super(appController);
    }

    //Risposte controllore
    public void respondError(String message, String tokenMatch) throws RemoteException {
        // implementazione
    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {
        // implementazione
    }

    //Osservazione partita
    public void onPlayerLeave(String tokenMatch, Player player) throws RemoteException {
        // implementazione
    }
    public void onPlayerRejoin(String tokenMatch, Player player) throws RemoteException {
        // implementazione
    }

    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        // implementazione
    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        // implementazione
    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        // implementazione
    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        // implementazione
    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {
        // implementazione
    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {
        // implementazione
    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {
        // implementazione
    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        // implementazione
    }
}
