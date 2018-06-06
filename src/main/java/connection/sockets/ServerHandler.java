package connection.sockets;

import mvc.controller.AppController;
import mvc.model.AppModel;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;

public class ServerHandler implements AppViewStub {
    //Gestore del server
    private ConnectionHandler connectionHandler;
    private AppController controller;

    //Costruttori
    public ServerHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.controller = new AppController();
    }

    @Override
    public AppControllerStub getAppController() {
        return null;
    }

    @Override
    public String login(String name) throws RemoteException {
        return null;
    }

    @Override
    public void logout() throws RemoteException {

    }

    @Override
    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //TODO: implementazione
    }

    @Override
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {
        //TODO: implementazione
    }

    @Override
    public void onMatchStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    @Override
    public void onChooseWindows(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    @Override
    public void onTurnStart(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    @Override
    public void onTurnEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

    @Override
    public void onPlaceDie(String tokenMatch, MultiPlayerMatch match, Cell cell, Die die) throws RemoteException {

    }

    @Override
    public void onUseTool(String tokenMatch, MultiPlayerMatch match, ToolCard toolCard) throws RemoteException {

    }

    @Override
    public void onGetPoints(String tokenMatch, MultiPlayerMatch match, Player player, PlayerPoints points) throws RemoteException {

    }

    @Override
    public void onMatchEnd(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }

        @Override
    public void respondError(String message) throws RemoteException {

    }

    @Override
    public void respondAck(String message) throws RemoteException {

    }
}
