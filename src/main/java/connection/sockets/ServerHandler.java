package connection.sockets;

import mvc.model.objects.*;
import mvc.stubs.AppControllerStub;
import mvc.stubs.AppViewStub;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerHandler implements Runnable, AppViewStub {
    //Gestore dei client nel server

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    //Costruttori
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            this.handleRequest();

        } catch(IOException e){
            e.printStackTrace();

        } finally {
            try{
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                outputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            try{
                SocketServer.getIstance().close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void handleRequest(){

    }


    @Override
    public AppControllerStub getAppController() {
        return null;
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
    public String login(String name) throws RemoteException {
        return null;
    }

    @Override
    public void logout() throws RemoteException {

    }

    @Override
    public void respondError(String message) throws RemoteException {

    }

    @Override
    public void respondAck(String message) throws RemoteException {

    }
}
