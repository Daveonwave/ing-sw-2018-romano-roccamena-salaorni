package connection.sockets.clientside;

import connection.sockets.communication.IOSupport;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.server.ServerRequest;

public class ClientTransmitter implements Runnable {
    //Gestore dello smistamento delle richieste del server

    private ClientActionHandler clientActionHandler;
    private ResponseRegistry responseRegistry;
    private SocketClient client;
    private boolean isRunning;

    //Costruttori
    public ClientTransmitter(SocketClient client, ClientActionHandler clientActionHandler) {
        this.clientActionHandler = clientActionHandler;
        this.client = client;
        this.responseRegistry = client.getResponseRegistry();
    }

    //Setter/Getter
    public void setRunning(boolean running) {
        isRunning = running;
    }

    //Sempre attivo per la ricezione e gestione delle richieste
    public void run() {
         try {
             while (isRunning){
                 if(client.getIn().available() != 0) {

                     if (IOSupport.fromServer(client.getIn()) instanceof ServerRequest) {
                         ServerResponse response = ((ServerRequest) IOSupport.fromServer(client.getIn())).handleAction(clientActionHandler);

                         if (response != null)
                             IOSupport.responseToServer(client.getOut(), response);

                     } else {
                         ClientResponse response = (ClientResponse) IOSupport.fromServer(client.getIn());
                         responseRegistry.insert(response);
                     }
                 }
             }
         } catch (Exception e) {
            e.printStackTrace();
         }
    }


}
