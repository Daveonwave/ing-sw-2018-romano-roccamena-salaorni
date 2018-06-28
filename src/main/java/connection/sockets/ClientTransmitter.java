package connection.sockets;

import connection.sockets.communication.ClientActionHandler;
import connection.sockets.communication.handlers.ServerRequestHandler;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.rmi.RemoteException;

public class ClientTransmitter implements Runnable {
    //Gestore dello smistamento delle richieste del server

    private ClientActionHandler clientActionHandler;
    private SocketClient client;
    private boolean isRunning;

    //Costruttori
    public ClientTransmitter(SocketClient client, ClientActionHandler clientActionHandler) {
        this.clientActionHandler = clientActionHandler;
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
    public void run() {
        while (isRunning) {
            try {
                if(IOSupport.fromServer(client.getIn()) instanceof ServerRequest) {
                    ServerResponse response =((ServerRequest) IOSupport.fromServer(client.getIn())).handleAction(clientActionHandler);

                    if (response != null)
                        IOSupport.responseToServer(client.getOut(), response);
                } else{
                    ClientResponse response = (ClientResponse) IOSupport.fromServer(client.getIn());
                    response.handleAction(clientActionHandler);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
