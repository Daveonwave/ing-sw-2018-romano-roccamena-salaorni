package mvc.model;

import mvc.model.ModelException;

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

        return token;
    }

    //Crea un nuovo oggetto registrato e restituisce token associato
    public String createObject(T obj) {
        String token = getFreeToken();
        put(token, obj);
        return token;
    }
    //Distrugge l'oggetto registrato attraverso il suo token
    public void destroyObject(String token) throws ModelException {
        T alias = get(token);

        if (alias != null)
            throw new ModelException("unknown token " + token);

        remove(token);
    }
}
