package service.call;

import service.ServiceException;
import service.ServiceNotifier;
import service.ServiceUpdater;
import service.SyncHandler;

import java.io.Serializable;

<<<<<<< HEAD:src/main/java/service/CallerUser.java
public abstract class CallerUser<E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceNotifier<E, I, O>{
=======
public abstract class CallerUser<E extends Serializable, I extends Serializable, O extends Serializable> extends SyncHandler<E, I, O> implements ServiceNotifier<E, I, O> {
>>>>>>> origin/master:src/main/java/service/call/CallerUser.java
    //Generico utilizzatore astratto di servizi che esegue una richiesta diretta al controllore
    //Richiesta a controllo locale
    //Richiesta a controllo remoto

    private ServiceUpdater<E, I, O> serviceUpdater;

    //Costruttori
    public CallerUser(ServiceUpdater<E, I, O> serviceUpdater) {
        super(new Object());
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
