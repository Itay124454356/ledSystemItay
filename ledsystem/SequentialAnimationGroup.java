package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class SequentialAnimationGroup implements Animation {
    private Animation[] animations;
    private double durationPerAnimation; 
    private StopWatch stopWatch;

 
    public SequentialAnimationGroup(double durationPerAnimation, Animation... animations) {
        this.durationPerAnimation = durationPerAnimation;
        this.animations = animations;
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
    }

    @Override
    public void apply(LedStrip strip) {
        if (animations == null || animations.length == 0) return;

        double elapsedTime = stopWatch.get();

        int currentIndex = (int) (elapsedTime / durationPerAnimation);

        if (currentIndex < animations.length) {
            animations[currentIndex].apply(strip);
        }
    }
}