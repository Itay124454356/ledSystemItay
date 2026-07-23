package ledsystem;

import java.awt.Color;
import java.util.HashMap;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class LedController {
    private LedStrip strip;
    private Animation animation;
    private StopWatch stopwatch;
    private double duration;

    private HashMap<RobotState, Animation> stateAnimations = new HashMap<>();
    private double defaultDuration = 5.0; 

    public LedController(LedStrip strip) {
        this.strip = strip;
        this.stopwatch = new StopWatch();
    }

    public void setAnimationForState(RobotState state, Animation animation) {
        this.stateAnimations.put(state, animation);
    }

    public void playState(RobotState state) {
        if (stateAnimations.containsKey(state)) {
            Animation selectedAnimation = stateAnimations.get(state);
            
            this.addAnimation(selectedAnimation, this.defaultDuration);
            this.play();
        }
    }

    public void addAnimation(Animation animation, double duration) {
        this.animation = animation;
        this.duration = duration;
        this.stopwatch.start(); 
    }

    public void play() {
        while (animation != null && stopwatch.get() < duration) {
            animation.apply(strip);
        }

        strip.setAll(Color.WHITE);
        strip.apply();
        this.animation = null;
    }
}