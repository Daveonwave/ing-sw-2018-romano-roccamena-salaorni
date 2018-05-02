package mvc.stubs;

import java.rmi.Remote;

public interface ViewResponder extends Remote {
    //Risposta della view al controllore

    void respondError(String message);
    void respondAck(String message);
}
