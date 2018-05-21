package util;


import java.util.Timer;
import java.util.TimerTask;

public abstract class TimedTaskHandler extends TimerTask {
    //Gestore di evento temporale

    private Timer timer;
    private int delay;

    //Costruttori
    public TimedTaskHandler(int delay) {
        this.timer = new Timer();
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

    //Task del timer
    public synchronized void run() {
        try {
            onTimedTask();
        } catch (Exception e) {
            onTimedTaskException(e);
        }
    }

    //Evento sul task
    public abstract void onTimedTask() throws Exception;
    public abstract void onTimedTaskException(Exception e);

    //Operazioni su gestore
    public synchronized void start() {
        timer.schedule(this, delay);
    }
    public synchronized void stop() { timer.cancel(); }

}