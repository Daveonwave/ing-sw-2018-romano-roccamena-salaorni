package connection.sockets.communication;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sender of the messages in the socket connection server side
 */
public class ServerWriter {

    private static final Logger LOGGER = Logger.getLogger(ServerWriter.class.getName());

    /**
    * Send a server request to the client
    * @param outputStream stream in which the server writes
    * @param request request to send
    */
    public synchronized void requestToClient(ObjectOutputStream outputStream, ServerRequest request) {

        try {
            outputStream.writeObject(request);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio richiesta al client");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: invio richiesta al client fallito");
        }
    }
    /**
     * Send a response to the client
     * @param outputStream stream in which the server writes
     * @param response response to send
     */
    public synchronized void responseToClient(ObjectOutputStream outputStream, ClientResponse response) {

        try {
            outputStream.writeObject(response);
            outputStream.flush();
            LOGGER.log(Level.INFO,"[SOCKET]: invio risposta al client");

        } catch(IOException e){
            LOGGER.log(Level.WARNING, "[ERROR]: invio risposta al client fallito");
        }
    }
}
