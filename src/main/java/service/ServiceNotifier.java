package service;

public interface ServiceNotifier<E, I, O> {
    //Generico notifier di richieste di servizi

    O notifyRequest(E event, I input) throws ServiceException;
}
