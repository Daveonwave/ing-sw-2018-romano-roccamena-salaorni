package connection.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOSupport {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    //Serializza e manda l'oggetto al server
    public static void send(ObjectOutputStream outputStream, Object obj) {

        try {
            outputStream.writeObject(obj);
            outputStream.flush();
            System.out.println("[SOCKET] Riposta serializzata e scritta");

        } catch(IOException e){
            e.printStackTrace();
        }
    }


    //Riceve dal server e deserializza l'oggetto
    public static Object receive(ObjectInputStream inputStream) {
        Object obj = null;

        try {
            obj = inputStream.readObject();
            System.out.println("[SOCKET] Riposta letta e deserializzata");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return obj;
    }
}
