package mvc.stubs;

import java.io.Serializable;

public interface AppViewStub extends UserView, ViewResponder, MultiPlayerObserver, Serializable {
    //Interfaccia di view dell'applicazione

    public AppControllerStub getAppController();
}
