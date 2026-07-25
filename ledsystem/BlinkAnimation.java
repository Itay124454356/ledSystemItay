package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class BlinkAnimation implements Animation {
    private Color color;
    private StopWatch stopWatch;

    public BlinkAnimation(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null.");
        }

        this.color = color;
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
    }

    @Override
    public void apply(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }

        if (stopWatch.get() % 4 < 2) {
            strip.setAll(Color.WHITE);
        } else {
            strip.setAll(this.color);
        }
        strip.apply();
    }
}