package service;

import java.rmi.RemoteException;

public class ServiceException extends RemoteException {
    //Eccezione di un servizio remoto

    public ServiceException(String message) {
        super(message);
    }
}
