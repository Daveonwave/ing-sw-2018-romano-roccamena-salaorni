package config;

/**
 * Configuration object containing controller's timers parameters
 */
public class TimerConfig {
    //Configurazioni su timer del controllore

    private int joinWaitTime;
    private int turnMaxTime;

    //Costruttori
    public TimerConfig(int joinWaitTime, int turnMaxTime) {
        this.joinWaitTime = joinWaitTime;
        this.turnMaxTime = turnMaxTime;
    }

    //Setter/Getter
    public void setJoinWaitTime(int joinWaitTime) {
        this.joinWaitTime = joinWaitTime;
    }
    public void setTurnMaxTime(int turnMaxTime) {
        this.turnMaxTime = turnMaxTime;
    }

    public int getJoinWaitTime() {
        return joinWaitTime;
    }
    public int getTurnMaxTime() {
        return turnMaxTime;
    }
}
