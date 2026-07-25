package ledsystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class LedController {
    private LedStrip strip;
    private List<Animation> animations;
    private List<Double> durations;
    private StopWatch stopwatch;

    public LedController(LedStrip strip) {
        this.strip = strip;
        this.animations = new ArrayList<>();
        this.durations = new ArrayList<>();
        this.stopwatch = new StopWatch();
    }

    public void addAnimation(Animation animation, double duration) {
        if (animation != null) {
            this.animations.add(animation);
            this.durations.add(duration);
        }
    }

    public void play() {
        for (int i = 0; i < animations.size(); i++) {
            Animation animation = animations.get(i);
            double duration = durations.get(i);

            stopwatch.start();
            while (stopwatch.get() < duration) {
                animation.apply(strip);
            }
        }

        strip.setAll(Color.WHITE);
        strip.apply();
        this.animations.clear();
        this.durations.clear();
    }
}