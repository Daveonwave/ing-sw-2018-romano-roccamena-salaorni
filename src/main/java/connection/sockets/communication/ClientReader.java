package connection.sockets.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Receiver of the messages in the socket bridge client side
 */
public class ClientReader {

    private static final Logger LOGGER = Logger.getLogger(ClientReader.class.getName());

    /**
    * Receive an object from the server that is a response to a client request or a request of the server
    * @param inputStream stream from which the client reads.
    * @return the content of the stream
    */
    public synchronized Object fromServer(ObjectInputStream inputStream){
        Object response = null;

        try {
            response = inputStream.readObject();
            LOGGER.log(Level.INFO,"[SOCKET]: ricezione oggetto generico dal server");

        } catch (IOException ioe) {
            LOGGER.log(Level.WARNING, "[ERROR]: ricezione oggetto generico dal server fallita");
        } catch (ClassNotFoundException cnfe){
            LOGGER.log(Level.WARNING, "[ERROR]: classe oggetto ricevuto non trovata");
        }
        return response;
    }
}
