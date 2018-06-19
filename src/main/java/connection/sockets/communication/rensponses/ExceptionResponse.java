package connection.sockets.communication.rensponses;

import java.rmi.RemoteException;

public abstract class ExceptionResponse {

    private boolean isException;
    private RemoteException exception = null;

    //Setter/Getter
    public boolean isException() {
        return isException;
    }
    public RemoteException getException() {
        return exception;
    }

    public void setExceptionFlag(boolean exception) {
        isException = exception;
    }
    public void setException(RemoteException exception) {
        this.exception = exception;
    }



}
