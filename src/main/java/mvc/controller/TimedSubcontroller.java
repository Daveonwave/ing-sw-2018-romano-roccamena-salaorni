package mvc.controller;

import util.TimedTaskHandler;

public abstract class TimedSubcontroller extends TimedTaskHandler {
    //Gestore di task del controllore a tempo

    private AppController controller;

    //Costruttori
    public TimedSubcontroller(AppController controller, int delay) {
        super(delay);
        this.controller = controller;
    }

    //Setter/Getter
    public AppController getController() {
        return controller;
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }

    //Eventi task
    public abstract void onTimedTask() throws Exception;
    public abstract void onTimedTaskException(Exception e);
}
