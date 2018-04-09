package service;

public interface ServiceNotifier<E, I, O> {
    //Generico notifier di eventi di servizi

    O notifyEvent(E event, I input) throws ServiceException;
}
