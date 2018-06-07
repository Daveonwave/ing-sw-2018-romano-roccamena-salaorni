package connection.sockets.actionshandling;

import java.io.Serializable;

public interface Response extends Serializable {
    //Risposta dal server al client

    void handleAction (ResponseHandler handler);
}
