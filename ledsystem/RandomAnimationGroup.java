package ledsystem;

import java.util.Random;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class RandomAnimationGroup implements Animation {
    private Animation[] animations;
    private double durationPerAnimation; 
    private StopWatch stopWatch;
    private Random random;
    private int currentRandomIndex;

    public RandomAnimationGroup(double durationPerAnimation, Animation... animations) {
        if (durationPerAnimation <= 0) {
            throw new IllegalArgumentException("Duration per animation must be greater than zero.");
        }
        if (animations == null || animations.length == 0) {
            throw new IllegalArgumentException("Animations array cannot be null or empty.");
        }
        for (Animation anim : animations) {
            if (anim == null) {
                throw new IllegalArgumentException("Animation in group cannot be null.");
            }
        }

        this.durationPerAnimation = durationPerAnimation;
        this.animations = animations;
        this.stopWatch = new StopWatch();
        this.random = new Random();
        
        this.stopWatch.start();
        this.currentRandomIndex = random.nextInt(animations.length);
    }

    @Override
    public void apply(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }

        if (stopWatch.get() >= durationPerAnimation) {
            currentRandomIndex = random.nextInt(animations.length);
            stopWatch.start(); 
        }

        animations[currentRandomIndex].apply(strip);
    }
}