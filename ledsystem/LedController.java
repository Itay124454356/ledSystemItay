package ledsystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class LedController {
    private LedStrip strip;
    private List<Animation> animations;
    private List<Double> durations;
    private StopWatch stopwatch;

    private Map<RobotState, Animation> stateAnimations;
    private double defaultDuration = 5.0;

    public LedController(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }
        this.strip = strip;
        this.animations = new ArrayList<>();
        this.durations = new ArrayList<>();
        this.stopwatch = new StopWatch();
        this.stateAnimations = new HashMap<>();
    }

    public void setAnimationForState(RobotState state, Animation animation) {
        if (state == null) {
            throw new IllegalArgumentException("RobotState cannot be null.");
        }
        if (animation == null) {
            throw new IllegalArgumentException("Animation cannot be null.");
        }
        this.stateAnimations.put(state, animation);
    }

    public void playState(RobotState state) {
        if (state == null) {
            throw new IllegalArgumentException("RobotState cannot be null.");
        }
        if (!stateAnimations.containsKey(state)) {
            throw new IllegalArgumentException("No animation registered for state: " + state);
        }

        Animation selectedAnimation = stateAnimations.get(state);
        this.addAnimation(selectedAnimation, this.defaultDuration);
        this.play();
    }

    public void addAnimation(Animation animation, double duration) {
        if (animation == null) {
            throw new IllegalArgumentException("Animation cannot be null.");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero.");
        }

        this.animations.add(animation);
        this.durations.add(duration);
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