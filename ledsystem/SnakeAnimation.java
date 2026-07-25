package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class SnakeAnimation implements Animation {
    private Color color;
    private Color backgroundColor;
    private StopWatch stopWatch;
    private double speed;
    private int length; 

    private boolean isInitialized = false;
    private int lastHead = -1;

    public SnakeAnimation(Color color, Color backgroundColor, double speed, int length) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null.");
        }
        if (backgroundColor == null) {
            throw new IllegalArgumentException("Background color cannot be null.");
        }
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be greater than zero.");
        }

        this.color = color;
        this.backgroundColor = backgroundColor;
        this.speed = speed;
        this.length = Math.max(1, length); 
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
    }

    @Override
    public void apply(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }

        int totalLeds = strip.getLedCount();
        if (totalLeds == 0) return;

        if (!isInitialized) {
            strip.setAll(this.backgroundColor);
            isInitialized = true;
        }

        int currentHead = (int) (stopWatch.get() * speed) % totalLeds;

        if (currentHead == lastHead) {
            return;
        }

        if (lastHead != -1) {
            for (int i = 0; i < length; i++) {
                int prevSegment = (lastHead - i + totalLeds) % totalLeds;
                strip.setLed(this.backgroundColor, prevSegment);
            }
        }

        for (int i = 0; i < length; i++) {
            int currentSegment = (currentHead - i + totalLeds) % totalLeds;
            strip.setLed(this.color, currentSegment);
        }

        lastHead = currentHead;

        strip.apply();
    }
}