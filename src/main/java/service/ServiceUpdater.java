package service;

import java.rmi.Remote;

public interface ServiceUpdater<E, I, O> extends Remote {
    //Generico updater remoto di servizi

    O updateEvent(E event, I input) throws ServiceException;
}
