package connection.sockets.actionshandling;

import java.io.Serializable;

public interface Request extends Serializable {
    //Domanda dal client al server

    Response handleAction (RequestHandler handler);

}
