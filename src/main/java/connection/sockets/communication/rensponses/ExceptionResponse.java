package connection.sockets.communication.rensponses;

public abstract class ExceptionResponse {

    private boolean isException;

    //Setter/Getter
    public boolean isException() {
        return isException;
    }
    public void setException(boolean exception) {
        isException = exception;
    }
}
