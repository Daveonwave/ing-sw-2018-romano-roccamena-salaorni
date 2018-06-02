package connection.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrasmissionFunctions {
    //Classe utile per mandare e ricevere oggetti - nome da cambiare

    //Serializza e manda l'oggetto
    public Object sendObject(ObjectOutputStream outputStream) {
        Object obj = null;

        try {
            outputStream.writeObject(obj);
            outputStream.flush();
            System.out.println("oggetto scritto!");

        } catch(IOException e){
            e.printStackTrace();
        }
        return obj;
    }

    //Riceve e deserializza l'oggetto
    public Object receiveObject(ObjectInputStream inputStream) {
        Object obj = null;

        try {
            obj = inputStream.readObject();
            System.out.println("oggetto letto!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return obj;
    }
}
