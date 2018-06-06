package connection.sockets;

import connection.Client;
import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;
import mvc.view.AppView;

import java.net.Socket;
import java.rmi.RemoteException;

public class ClientHandler implements AppControllerStub {
    //Gestore del client

    private final SocketClient client;

    private AppView view;

    //Costruttori
    public ClientHandler(SocketClient client) {
        this.client = client;
    }


    public void run(){

    }

    @Override
    public String login(String name, AppViewStub view) throws RemoteException {
        this.view = (AppView) view;

        return null;
    }

    @Override
    public void logout(String tokenUser) throws RemoteException {

    }

    @Override
    public void joinMatch(String tokenUser) throws RemoteException {

    }

    @Override
    public void cancelJoinMatch(String tokenUser) throws RemoteException {

    }

    @Override
    public void leaveMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }

    @Override
    public void rejoinMatch(String tokenUser, String tokenMatch) throws RemoteException {

    }

    @Override
    public void chooseWindow(String tokenUser, String tokenMatch, Window window) throws RemoteException {

    }

    @Override
    public void placeDie(String tokenUser, String tokenMatch, Cell cell, Die die) throws RemoteException {

    }

    @Override
    public void useToolCard(String tokenUser, String tokenMatch, ToolCardInput input, ToolCard toolCard) throws RemoteException {

    }

    @Override
    public void endTurn(String tokenUser, String tokenMatch) throws RemoteException {

    }


}
