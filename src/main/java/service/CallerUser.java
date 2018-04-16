package service;

import java.io.Serializable;

public abstract class CallerUser<E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceNotifier<E, I, O>{
    //Generico utilizzatore astratto di servizi che esegue una richiesta diretta al controllore
    //Richiesta a controllo locale
    //Richiesta a controllo remoto

    private ServiceUpdater<E, I, O> serviceUpdater;

    //Costruttori
    public CallerUser(ServiceUpdater<E, I, O> serviceUpdater) {
        super();
        this.serviceUpdater = serviceUpdater;
    }

    //Setter/Getter
    protected void setServiceUpdater(ServiceUpdater<E, I, O> serviceUpdater) {
        this.serviceUpdater = serviceUpdater;
    }

    protected ServiceUpdater<E, I, O> getServiceUpdater() {
        return serviceUpdater;
    }

    //Notify
    public O notifyRequest(E event, I input) throws ServiceException {
        return raiseTask(event, input);
    }
    protected O handleTask(E event, I input) throws ServiceException {
        return serviceUpdater.updateRequest(event, input);
    }
}
