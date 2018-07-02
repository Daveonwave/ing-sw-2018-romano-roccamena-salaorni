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
            LOGGER.log(Level.WARNING, "[ERROR]: receiving sendRequest from client failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received sendRequest from client doesn't match with any class");
        }
        return request;
    }
    public static synchronized ServerResponse responseFromClient(ObjectInputStream inputStream) {
        ServerResponse response = null;

        try {
            response = (ServerResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal client");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving response from client failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received response from client doesn't match with any class");
        }
        return response;
    }
}
