package connection.sockets.communication;

import connection.sockets.communication.rensponses.server.ServerResponse;
import connection.sockets.communication.requests.client.ClientRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerReader {

    private static final Logger LOGGER = Logger.getLogger(ServerReader.class.getName());

    public static synchronized ClientRequest requestFromClient(ObjectInputStream inputStream) {
        ClientRequest request = null;

        try {
            request = (ClientRequest) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione richiesta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: ricezione richiesta dal client fallita");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: classe richiesta non trovata");
        }
        return request;
    }
    public static synchronized ServerResponse responseFromClient(ObjectInputStream inputStream) {
        ServerResponse response = null;

        try {
            response = (ServerResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: ricezione risposta dal client fallita");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: classe risposta non trovata");
        }
        return response;
    }
}
