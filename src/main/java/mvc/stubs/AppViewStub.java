package mvc.stubs;

import java.io.Serializable;

public interface AppViewStub extends UserView, ViewResponder, MatchObserver, Serializable {
    //Interfaccia di view dell'applicazione

    public AppControllerStub getAppController();
}
