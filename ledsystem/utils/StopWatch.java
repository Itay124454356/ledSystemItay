package ledsystem.utils;

import java.time.Instant;

public class StopWatch {
    
    private Double startTime;

    /*
     * This is the constructor. Use it to construct the Stopwatch.
     */
    public StopWatch() {}

    /*
     * This starts the stopwatch. You can reuse it to restart the stopwatch.
     */
    public void start() {
        startTime = getTime();
    }

    /*
     * This return the current time. If you don't call the `start` method you will get an error.
     */
    public double get() {
        if (startTime.isNaN()) {
            throw new RuntimeException("HELPPPPP! Please initiate stopwatchh!");
        }
        return getTime() - this.startTime;
    }

    /*
     * THIS DOES NOT CONCERN YOU. PLEASE DONT ASK QUESTIONS
     */
    private double getTime() {
        return Instant.now().toEpochMilli() / 1000;
    }
}
