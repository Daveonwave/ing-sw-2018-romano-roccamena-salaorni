package service;

import java.io.Serializable;

public abstract class SyncHandler<E extends Serializable, I extends Serializable, O extends Serializable> {
    //Generico gestore astratto di un task

    private Object lock;

    
    //Lancia la computazione del task
    //Ottiene il lock per la chiamata alla computazione
    protected O raiseTask(E event, I input) throws ServiceException {
        O result = null;

        //Lock sull'intero oggetto
        synchronized (this) {
            result = handleTask(event, input);
        }

        return result;
    }

    //Task astratto
    protected abstract O handleTask(E event, I input) throws ServiceException{

    }
}
