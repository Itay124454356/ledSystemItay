package ledsystem;
import java.awt.Color;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;
public class BlinkAnimation implements Animation {
    private Color color;
    private StopWatch stopWatch;

    public BlinkAnimation(Color color) {
        this.color = color;
        this.stopWatch = new StopWatch();
        stopWatch.start();
    }

    @Override
    public void apply(LedStrip strip) {
        
    
        if (stopWatch.get()%4 < 2) {
            strip.setAll(this.color);
        } else {
            strip.setAll(Color.WHITE);
        }
        strip.apply();
    
    }
}