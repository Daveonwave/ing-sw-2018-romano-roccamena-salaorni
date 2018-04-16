package service;

import java.rmi.Remote;

public interface ServiceUpdater<E, I, O> extends Remote {
    //Generico updater remoto di richieste di servizi

    O updateRequest(E event, I input) throws ServiceException;
}
