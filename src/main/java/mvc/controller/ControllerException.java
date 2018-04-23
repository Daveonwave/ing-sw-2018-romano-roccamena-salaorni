package mvc.controller;

import java.rmi.RemoteException;

public class ControllerException extends RemoteException {
    //Eccezione del controllore dell'applicazione

    //Costruttori
    public ControllerException(String message) {
        super(message);
    }
}