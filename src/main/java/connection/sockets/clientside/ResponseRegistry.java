package connection.sockets.clientside;

import connection.sockets.communication.rensponses.client.ClientResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry that is filled with the responses received from server and that have to be handled
 */
public class ResponseRegistry {

    private Map<Integer, ClientResponse> registry;

    /**
     * Constructor of the registry that creates a map, where the key is the id of the response and the value is the
     * associated response.
     */
    public ResponseRegistry() {
        registry = new HashMap<>();
    }

    /**
     * Insertion of an entry (id, response) inside the registry
     * @param response specific response to insert
     */
    public synchronized void insert(ClientResponse response){
        registry.put(response.getIdAction(), response);

        notifyAll();
    }

    //Elimina la risposta dalla mappa
    /**
     * Remove the entry from the map
     * @param responseId
     */
    public synchronized void delete(int responseId){
        registry.remove(responseId);
    }

    //Cerca la risposta nella mappa
    /**
     * Search a specific response by id
     * @param responseId id of the response
     * @return the response searched
     */
    public synchronized ClientResponse search(int responseId){
        return registry.get(responseId);
    }

    //Ottiene la risposta dalla pila e libera la mappa
    /**
     * Get the response by id. The method is stopped when the response isn't inside the map and it needs to be waited
     * @param responseId id of the response
     * @return the response
     */
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
