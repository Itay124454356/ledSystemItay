package ledsystem;

import ledsystem.ledssim.LedStrip;

public class LedController {
    private LedStrip strip;
    private Animation animation;

    public LedController(LedStrip strip) {
        this.strip = strip;
    }

    public void addAnimation(Animation animation) {
        this.animation = animation;
    }
    public void play() {
        if (animation != null) {
            animation.apply(strip);
        }
    }
}