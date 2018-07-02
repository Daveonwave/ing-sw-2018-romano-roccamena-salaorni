package connection.sockets.communication;

import connection.sockets.communication.rensponses.client.ClientResponse;
import connection.sockets.communication.requests.server.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientReader {

    private static final Logger LOGGER = Logger.getLogger(ClientReader.class.getName());

    public static synchronized ServerRequest requestFromServer(ObjectInputStream inputStream) {
        ServerRequest request = null;

        try {
            request = (ServerRequest) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione richiesta dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving sendRequest from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received sendRequest from server doesn't match with any class");
        }
        return request;
    }
    public static synchronized ClientResponse responseFromServer(ObjectInputStream inputStream) {
        ClientResponse response = null;

        try {
            response = (ClientResponse) inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione risposta dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving response from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received response from server doesn't match with any class");
        }

        return response;
    }

    public static synchronized Object fromServer(ObjectInputStream inputStream){
        Object response = null;

        try {
            response = inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione oggetto generico dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: receiving object from server failed");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: received object from server doesn't match with any class");
        }
        return response;
    }
}
