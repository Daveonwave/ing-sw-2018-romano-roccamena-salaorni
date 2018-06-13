package connection.sockets;

import mvc.controller.AppController;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.rmi.RemoteException;


public class ViewProxy implements AppViewStub {
    //Proxy della view utilizzato lato server

    private ServerTransmitter serverTransmitter;
    private AppController controller;

    //Costruttori
    public ViewProxy(ServerTransmitter serverTransmitter) {
        this.serverTransmitter = serverTransmitter;
        this.controller = new AppController();
    }

    public AppControllerStub getController() throws RemoteException {
        return null;
    }

    public String login(String userToken) throws RemoteException {
        return null;
    }
    public void logout() throws RemoteException {

    }

    public void onPlayerLeave(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
    public void onPlayerRejoin(String tokenMatch, MultiPlayerMatch match) throws RemoteException {

    }
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

    public void respondError(String message, String tokenMatch) throws RemoteException {

    }
    public void respondAck(String message, String tokenMatch) throws RemoteException {

    }
}
