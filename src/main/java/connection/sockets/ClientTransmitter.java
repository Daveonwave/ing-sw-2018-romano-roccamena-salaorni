package connection.sockets;

import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.server.ServerResponse;

import java.io.IOException;

public class ClientTransmitter implements Runnable {
    //Gestore dello smistamento delle richieste del server

    private ServerRequestHandler serverRequestHandler;
    private SocketClient client;
    private boolean isRunning;

    //Costruttori
    public ClientTransmitter(SocketClient client, ServerRequestHandler serverRequestHandler) {
        this.serverRequestHandler = serverRequestHandler;
        this.client = client;
    }

    //Setter/Getter
    public void setRunning(boolean running) {
        isRunning = running;
    }
    public boolean isRunning() {
        return isRunning;
    }

    //Sempre attivo per la ricezione e gestione delle richieste
    public void run(){
        try{
            while(isRunning){
                ServerResponse response = IOSupport.requestFromServer(client.getIn()).handleAction(serverRequestHandler);

                if(response != null)
                    IOSupport.responseToServer(client.getOut(), response);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
