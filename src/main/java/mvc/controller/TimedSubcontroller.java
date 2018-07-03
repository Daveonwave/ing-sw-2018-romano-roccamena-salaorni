package mvc.controller;

import util.TimedTaskHandler;

/**
 * Timed task extension of a subcontroller of the application
 */
public abstract class TimedSubcontroller extends TimedTaskHandler {
    //Gestore di task del controllore a tempo

    private AppController controller;

    //Costruttori
    /**
     * Create new timed subcontroller
     * @param controller Parent controller instance
     * @param delay Delay of timer
     */
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
}
