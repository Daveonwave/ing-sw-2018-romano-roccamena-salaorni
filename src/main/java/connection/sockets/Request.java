package connection.sockets;

import mvc.stubs.AppViewStub;

import java.io.Serializable;

public interface Request extends Serializable {
    //Domanda dal client al server

    Response handle (AppViewStub handler);

}
