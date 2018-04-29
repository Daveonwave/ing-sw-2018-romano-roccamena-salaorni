package mvc.model;

import mvc.exceptions.AppModelException;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;

public class TokenMap<T> extends HashMap<String, T> {
    //Generico map token-oggetto registrato costituente del modello

    //Calcola token libero
    private String getFreeToken() {
        String token;

        do {
            token = UUID.randomUUID().toString();
        } while(keySet().contains(token));

        return token;GameBuilder gameBuilder = new GameBuilder(false);
        List<PublicObjective> testRandomPublicObjectivesCard = new ArrayList<PublicObjective>();
        testRandomPublicObjectivesCard = gameBuilder.createPublicObjectives();
    }
    private void checkIsPresent(String token) throws RemoteException {
        T alias = get(token);

        if (alias != null)
            throw new AppModelException("unknown token " + token);
    }

    //Creazione
    public String createObject(T obj) {
        String token = getFreeToken();
        put(token, obj);
        return token;
    }
    public void destroyObject(String token) throws RemoteException {
        checkIsPresent(token);
        remove(token);
    }
    public void modifyObject(String token, T object) throws RemoteException {
        checkIsPresent(token);
        put(token, object);
    }


}
