package base;

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

    //Utente
    public String login(String name) throws RemoteException {
        return null;
    }
    public void logout() throws RemoteException {

    }

    //Risposte controllore
    public void respondError(String message) throws RemoteException {

    }
    public void respondAck(String message) throws RemoteException {

    }

    //Osservazione partita
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {

    }
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {

    }
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
}
