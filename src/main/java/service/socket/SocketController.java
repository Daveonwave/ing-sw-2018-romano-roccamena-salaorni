package service.socket;

import service.ServiceException;
import service.ServiceUpdater;
import service.SyncHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

<<<<<<< HEAD
public abstract class SocketController <E extends Serializable, I extends Serializable, O extends Serializable> implements ServiceUpdater<E, I, O>, Runnable {
=======
public abstract class SocketController <E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceUpdater<E, I, O>, Runnable {
>>>>>>> origin/master
    //Generico controllore astratto di servizi che è soggetto a richieste attraverso client dall'utilizzatore

    private final SocketServer server;
    private Socket client;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    //Costruttori
    public SocketController(SocketServer socketServer) {
        super(socketServer);
        this.server = socketServer;
    }

    //Setter/Getter
    public void setClient(Socket client) throws IOException {
        this.client = client;
        this.inStream = new ObjectInputStream(client.getInputStream());
        this.outStream = new ObjectOutputStream(client.getOutputStream());
    }

    public Socket getClient() {
        return client;
    }

    //Avvio controllore
    public void run() {
        try {
            //Ciclo di gestione del client
            while (client.isConnected()) {
                E event = (E) inStream.readObject();
                I input = (I) inStream.readObject();

                O result = raiseTask(event, input);

                outStream.writeObject(result);
                outStream.flush();
            }
        } catch (Exception e) {
           runExceptionHandling(e);
        }
    }
    protected abstract void runExceptionHandling(Exception e);

    //Update
    protected O handleTask(E event, I input) throws ServiceException {
        try {
            return updateRequest(event, input);
        } catch (Exception e) {
            throw new ServiceException("error while communicating with client");
        }
    }
    //Algoritmo di controllo astratto
    public abstract O updateRequest(E event, I input) throws ServiceException;

