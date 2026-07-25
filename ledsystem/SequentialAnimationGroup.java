package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class SequentialAnimationGroup implements Animation {
    private Animation[] animations;
    private double durationPerAnimation; 
    private StopWatch stopWatch;

    public SequentialAnimationGroup(double durationPerAnimation, Animation... animations) {
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
        this.stopWatch.start();
    }

    @Override
    public void apply(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }

        double elapsedTime = stopWatch.get();
        int currentIndex = (int) (elapsedTime / durationPerAnimation) % animations.length;

        animations[currentIndex].apply(strip);
    }
}