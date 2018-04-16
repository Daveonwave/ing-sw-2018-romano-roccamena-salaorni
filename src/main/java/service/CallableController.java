package service;

import java.io.Serializable;

public abstract class CallableController<E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceUpdater<E, I, O> {
    //Generico controllore astratto di servizi che è soggetto a richieste dirette dall'utilizzatore
    //Controllore locale
    //Controllore remoto

    //Costruttori
    public CallableController() {
        super();
    }

    //Update
    public O updateRequest(E event, I input) throws ServiceException {
        return raiseTask(event, input);
    }
    public abstract O handleTask(E event, I input) throws ServiceException;
}
