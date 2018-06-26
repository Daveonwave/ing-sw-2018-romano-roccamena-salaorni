package mvc.model;


import mvc.exceptions.AppModelException;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Map between online generic objects and a string token identifier
 * @param <T> Registered object type
 */
public class TokenMap<T> extends HashMap<String, T> {
    //Generico map token-oggetto registrato costituente del modello

    //Calcola token libero
    /**
     * Calculate new free token
     * @return
     */
    private String getFreeToken() {
        String token;

        do {
            token = UUID.randomUUID().toString();
        } while(keySet().contains(token));

        return token;
    }

    //Calcola se il token è registrato
    /**
     * Ensure given token is online
     * @param token Token of a registered object
     * @throws RemoteException AppModelException if token is not online
     */
    private void checkIsPresent(String token) throws RemoteException {
        T alias = get(token);

        if (alias == null)
            throw new AppModelException("errore di comunicazione token");
    }

    //Creazione
    /**
     * Register new object
     * @param obj Object to register
     * @return Token of the object
     */
    public String createObject(T obj) {
        String token = getFreeToken();
        put(token, obj);
        return token;
    }
    /**
     * Unregister a given object
     * @param token Token of the object
     * @throws RemoteException AppModelException if token is not found
     */
    public void destroyObject(String token) throws RemoteException {
        checkIsPresent(token);
        remove(token);
    }
    /**
     * Modify a given registered object state
     * @param token Token of the object
     * @param object Object to modify
     * @throws RemoteException AppModelException if token is not found
     */
    public void modifyObject(String token, T object) throws RemoteException {
        checkIsPresent(token);
        put(token, object);
    }


}
