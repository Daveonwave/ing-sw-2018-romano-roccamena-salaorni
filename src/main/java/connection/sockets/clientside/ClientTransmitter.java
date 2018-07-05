package connection.sockets.clientside;

import connection.sockets.communication.ClientReader;
import connection.sockets.communication.ClientWriter;
import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;
import connection.sockets.communication.requests.server.ServerRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread of the client always active that handle the messages received from the server
 */
public class ClientTransmitter implements Runnable {
    //Gestore dello smistamento delle richieste del server

    private ClientActionHandler clientActionHandler;
    private ResponseRegistry responseRegistry;
    private SocketClient client;

    private ClientReader clientReader;
    private ClientWriter clientWriter;

    private boolean isRunning;

    private static final Logger LOGGER = Logger.getLogger(ClientTransmitter.class.getName());

    /**
     * Constructor of the transmitter
     * @param client kernel of the client structure
     * @param clientActionHandler handler of the messages
     */
    public ClientTransmitter(SocketClient client, ClientActionHandler clientActionHandler) {
        this.clientActionHandler = clientActionHandler;
        this.client = client;
        this.responseRegistry = client.getResponseRegistry();
        clientReader = new ClientReader();
        clientWriter = new ClientWriter();
    }

    //Setter/Getter
    public void setRunning(boolean running) {
        isRunning = running;
    }

    //Sempre attivo per la ricezione e gestione delle richieste
    /**
     * Implemented from the interfaces and core method of this class
     */
    public void run() {
         try {
             while (isRunning){
                 Object received = clientReader.fromServer(client.getIn());

                 if (received instanceof ServerRequest) {
                     ServerResponse response = ((ServerRequest) received).handleAction(clientActionHandler);

                     if (response != null)
                         clientWriter.responseToServer(client.getOut(), response);

                 } else {
                     responseRegistry.insert((ClientResponse) received);
                 }
             }
         } catch (Exception e) {
            LOGGER.log(Level.WARNING, "[ERROR]: ricezione comunicazione con server fallita");
         }
    }

    /**
     * Get the response saved inside the storage of response (response registry)
     * @param idAction id of the wanted response
     * @return
     */
    public ClientResponse getResponse(int idAction){
        return responseRegistry.retrieveResponse(idAction);
    }
    /**
     * Send the sendRequest to server
     * @param request
     */
    public void sendRequest(ClientRequest request){
        clientWriter.requestToServer(client.getOut(), request);
    }
}
