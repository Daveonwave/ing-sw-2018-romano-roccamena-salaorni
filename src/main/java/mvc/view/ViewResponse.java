package mvc.view;

import java.rmi.Remote;

public interface ViewResponse extends Remote {
    //Controllore notifica la view in base alle richieste dell'utente

    void respondError(String message);
    void respondAck(String message);
}
