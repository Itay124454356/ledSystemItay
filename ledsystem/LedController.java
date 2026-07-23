package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class LedController {
    private LedStrip strip;
    private Animation animation;
    private StopWatch stopwatch;
    private double duration;

    public LedController(LedStrip strip) {
        this.strip = strip;
        this.stopwatch = new StopWatch();
    }

    public void addAnimation(Animation animation, double duration) {
        this.animation = animation;
        this.duration = duration;
        this.stopwatch.start(); 
    }

    // הקונטרולר עצמו מנהל את הרצת הזמן!
    public void play() {
        while (animation != null && stopwatch.get() < duration) {
            animation.apply(strip);
            
    
        }

        strip.setAll(Color.WHITE);
        strip.apply();
        this.animation = null;
    }
}