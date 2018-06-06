package connection.sockets;

import mvc.stubs.AppControllerStub;

import java.io.Serializable;

public interface Response extends Serializable {
    //Risposta dal server al client

    void handle (AppControllerStub handler);
}
