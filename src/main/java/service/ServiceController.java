package service;

import java.io.Serializable;

public abstract class ServiceController<E extends Serializable, I extends Serializable, O extends Serializable, S> implements ServiceUpdater<E, I, O> {
    //Generico controllore astratto di servizi

    private S serviceState;
    private boolean working;

    //Creatori
    public ServiceController(S serviceState) {
        this.serviceState = serviceState;
        this.working = true;
    }

    //Setter/Getter
    public void setServiceState(S serviceState) {
        this.serviceState = serviceState;
    }

    public S getServiceState() {
        return serviceState;
    }
    public boolean isWorking() {
        return working;
    }

    //Update
    public O updateEvent(E event, I input) throws ServiceException {
        O result = null;

        //Lock sull'intero oggetto
        synchronized (this) {
            working = true;
            result = handleEvent(event, input);

            if (result == null) {
                throw new ServiceException("event handling returned null");
            }
            working = false;
        }

        return result;
    }

    //Gestione ricezione eventi
    public abstract O handleEvent(E event, I input) throws ServiceException;
}
