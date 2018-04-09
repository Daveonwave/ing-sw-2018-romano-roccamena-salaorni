package service;

import java.io.Serializable;

public abstract class ServiceUser<E extends Serializable, I extends Serializable, O extends Serializable> implements ServiceNotifier<E, I, O>{
    //Generico utilizzatore di servizi

    private ServiceUpdater<E, I, O> serviceUpdater;
    private O serviceOutput;

    //Costruttori
    public ServiceUser(ServiceUpdater<E, I, O> serviceUpdater) {
        this.serviceUpdater = serviceUpdater;
        this.serviceOutput = null;
    }

    //Setter/Getter
    public void setServiceUpdater(ServiceUpdater<E, I, O> serviceUpdater) {
        this.serviceUpdater = serviceUpdater;
    }

    public ServiceUpdater<E, I, O> getServiceUpdater() {
        return serviceUpdater;
    }
    public O getServiceOutput() {
        return serviceOutput;
    }

    //Notify
    public O notifyEvent(E event, I input) throws ServiceException {
        serviceOutput = serviceUpdater.updateEvent(event, input);
        return serviceOutput;
    }
}
