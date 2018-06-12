package mvc.stubs;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface AppViewStub extends UserView, ViewResponder, MultiPlayerObserver, Serializable {
    //Interfaccia di view dell'applicazione

    public AppControllerStub getController() throws RemoteException;
}
