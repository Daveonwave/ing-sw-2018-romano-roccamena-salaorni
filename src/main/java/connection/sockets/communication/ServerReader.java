package connection.sockets.communication;

import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Receiver of the messages in the socket connection server side
 */
public class ServerReader {

    private static final Logger LOGGER = Logger.getLogger(ServerReader.class.getName());

    /**
    * Receive a request from the client
    * @param inputStream stream from which the server reads
    * @return request of the client
     * */
    public synchronized ClientRequest requestFromClient(ObjectInputStream inputStream) {
        ClientRequest request = null;

        try {
            request = (ClientRequest) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione richiesta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, ioe.getMessage());
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, cnfe.getMessage());
        }
        return request;
    }
    /**
     * Receive a response to a request of the server from client
     * @param inputStream stream from which the server reads
     * @return response relative to a server request
     */
    public synchronized ServerResponse responseFromClient(ObjectInputStream inputStream) {
        ServerResponse response = null;

        try {
            response = (ServerResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, ioe.getMessage());
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, cnfe.getMessage());
        }
        return response;
    }
}
