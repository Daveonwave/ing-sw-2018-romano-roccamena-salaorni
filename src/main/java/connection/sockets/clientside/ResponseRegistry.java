package connection.sockets.clientside;

import connection.sockets.communication.rensponses.client.ClientResponse;

import java.util.HashMap;
import java.util.Map;

public class ResponseRegistry {

    private Map<Integer, ClientResponse> registry;

    public ResponseRegistry() {
        registry = new HashMap<>();
    }

    //Insertion in the first position without a value
    public synchronized void insert(ClientResponse response){
        registry.put(response.getIdAction(), response);

        notifyAll();
    }

    //Elimina la risposta dalla pila
    public synchronized void delete(int responseId){
        registry.remove(responseId);
    }

    //Cerca la risposta nella pila
    public synchronized ClientResponse search(int responseId){
        return registry.get(responseId);
    }

    //Ottiene la risposta dalla pila e libera la mappa
    public synchronized ClientResponse retrieveResponse(int responseId){
        ClientResponse toRetrieve;

        toRetrieve = search(responseId);

        while(toRetrieve == null){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            toRetrieve = search(responseId);
        }
        delete(responseId);

        return toRetrieve;
    }

}
