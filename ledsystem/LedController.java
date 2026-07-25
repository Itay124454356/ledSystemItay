package ledsystem;

import java.util.ArrayList;
import java.util.List;
import ledsystem.ledssim.LedStrip;

public class LedController {
    private LedStrip strip;
    private List<Animation> animations;

    public LedController(LedStrip strip) {
        if (strip == null) {
            throw new IllegalArgumentException("LedStrip cannot be null.");
        }
        this.strip = strip;
        this.animations = new ArrayList<>();
    }

    public void addAnimation(Animation animation) {
        if (animation == null) {
            throw new IllegalArgumentException("Animation cannot be null.");
        }
        this.animations.add(animation);
    }

    public void play() {
        for (Animation animation : animations) {
            animation.apply(strip);
        }
    }
}