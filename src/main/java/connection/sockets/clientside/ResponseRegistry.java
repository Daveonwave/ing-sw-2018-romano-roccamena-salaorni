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
        if(registry.isEmpty()) {
            registry.put(0, response);
        }
        else{
            for(Integer i : registry.keySet()){
                if(!registry.containsValue(i)){
                    registry.putIfAbsent(i, response);
                    break;
                }
            }
        }
        notifyAll();
    }

    //Elimina la risposta dalla pila
    public synchronized void delete(ClientResponse response){
        ClientResponse toRemove = searchResponse(response.getIdAction());
        if(toRemove != null){
            registry.remove(toRemove);
        }
    }

    //Cerca la risposta nella pila
    public ClientResponse searchResponse(int responseId){
        for(ClientResponse res : registry.values()){
            if(res.getIdAction() == responseId){
                return res;
            }
        }
        return null;
    }

    //Ottiene la risposta dalla pila e libera la mappa
    public synchronized ClientResponse retrieveResponse(int responseId){
        ClientResponse toRetrieve;

        toRetrieve = searchResponse(responseId);

        while(toRetrieve == null){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        toRetrieve = searchResponse(responseId);
        }
        delete(toRetrieve);
        return toRetrieve;
    }

}
