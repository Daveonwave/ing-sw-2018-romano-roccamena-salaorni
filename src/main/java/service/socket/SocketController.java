package service.socket;

import service.ServiceException;
import service.ServiceUpdater;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class SocketController <E extends Serializable, I extends Serializable, O extends Serializable> implements ServiceUpdater<E, I, O>, Runnable {
    //Generico controllore astratto di servizi che è soggetto a richieste attraverso client dall'utilizzatore

    private final SocketServer server;
    private Socket client;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    //Costruttori
    public SocketController(SocketServer socketServer) {
        this.server = socketServer;
    }

    //Setter/Getter
    public void setClient(Socket client) {
        this.client = client;
    }

    public Socket getClient() {
        return client;
    }

    //Avvio controllore
    public void run() {
        try {
            inStream = new ObjectInputStream(client.getInputStream());
            outStream = new ObjectOutputStream(client.getOutputStream());

            //Ciclo di gestione del client
            while (client.isConnected()) {
                synchronized (server) {
                    E event = (E) inStream.readObject();
                    I input = (I) inStream.readObject();

                    outStream.writeObject(updateRequest(event, input));
                    outStream.flush();
                }
            }
        } catch (Exception e) {
           runExceptionHandling(e);
        }
    }
    protected abstract void runExceptionHandling(Exception e);

    //Update
    public abstract O updateRequest(E event, I input) throws ServiceException;
}
