package connection.sockets.communication;

import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sender of the messages in the socket bridge client side
 */
public class ClientWriter {

    private static final Logger LOGGER = Logger.getLogger(ClientWriter.class.getName());

    /**
     * Private class constructor
     */
    private ClientWriter(){}

    /**
     * Send a request to server
     * @param outputStream stream in which the client writes
     * @param request request relative to an action
     */
    public static synchronized void requestToServer(ObjectOutputStream outputStream, ClientRequest request){

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            LOGGER.log(Level.INFO, "[SOCKET]: invio richiesta al server");
        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: invio richiesta al server fallito");
        }
    }
    /**
     * Send a response to server
     * @param outputStream stream in which the client writes
     * @param response response relative to the request sent
     */
    public static synchronized void responseToServer(ObjectOutputStream outputStream, ServerResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio risposta al server");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: invio risposta al server fallito");
        }
    }
}
