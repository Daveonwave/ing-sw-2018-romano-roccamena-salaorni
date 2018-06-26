package util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstract handler of the execution of a given abstract task in separetad thread, with timer controlled behaviour
 */
public abstract class TimedTaskHandler extends TimerTask {
    //Gestore di evento temporale

    private Timer timer;
    private boolean working;
    private int delay;

    //Costruttori
    /**
     * Set the delay of timer control
     * @param delay Delay of timer
     */
    public TimedTaskHandler(int delay) {
        this.timer = new Timer();
        this.working = false;
        this.delay = delay;
    }

    //Setter/Getter
    public synchronized void setTimer(Timer timer) {
        this.timer = timer;
    }
    public synchronized void setDelay(int delay) {
        this.delay = delay;
    }

    public synchronized Timer getTimer() {
        return timer;
    }
    public synchronized int getDelay() {
        return delay;
    }

    public synchronized boolean isWorking() {
        return working;
    }

    //Task del timer

    /**
     * Task of the timer
     */
    public synchronized void run() {
        try {
            onTimedTask();
        } catch (Exception e) {
            onTimedTaskException(e);
        }
    }

    //Evento sul task

    /**
     * Task raised on timer waiting completition
     * @throws Exception Any kind of exception
     */
    public abstract void onTimedTask() throws Exception;
    /**
     * Event raised if task throws an exception
     * @param e Exception raised
     */
    public abstract void onTimedTaskException(Exception e);

    //Operazioni su gestore

    /**
     * Start the timer of the task
     */
    public synchronized void start() {
        if (working)
            return;

        this.working = true;
        timer.schedule(this, delay);
    }

    /**
     * Stop the timer of the task
     */
    public synchronized void stop() {
        if (!working)
            return;

        timer.cancel();
        this.working = false;
    }

}
