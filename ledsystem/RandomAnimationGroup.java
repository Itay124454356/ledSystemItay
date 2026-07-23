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
    private boolean isStarted = false;

    public RandomAnimationGroup(double durationPerAnimation, Animation... animations) {
        this.durationPerAnimation = durationPerAnimation;
        this.animations = animations;
        this.stopWatch = new StopWatch();
        this.random = new Random();
    }

    @Override
    public void apply(LedStrip strip) {
        if (animations == null || animations.length == 0) return;

        if (!isStarted) {
            stopWatch.start();
            currentRandomIndex = random.nextInt(animations.length);
            isStarted = true;
        }

        if (stopWatch.get() >= durationPerAnimation) {
            currentRandomIndex = random.nextInt(animations.length);
            stopWatch.start(); 
        }

        animations[currentRandomIndex].apply(strip);
    }
}