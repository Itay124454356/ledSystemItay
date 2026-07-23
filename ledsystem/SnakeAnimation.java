package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;

public class SnakeAnimation implements Animation {
    private Color color;
    private Color backgroundColor;
    private StopWatch stopWatch;
    private double speed;
    private int length; 

    private boolean isInitialized = false;
    private int lastHead = -1;


    public SnakeAnimation(Color color, Color backgroundColor, double speed, int length) {
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.speed = speed;
        this.length = Math.max(1, length); 
        this.stopWatch = new StopWatch();
        this.stopWatch.start();
    }



    @Override
    public void apply(LedStrip strip) {
        int totalLeds = strip.getLedCount();
        if (totalLeds == 0) return;

        if (!isInitialized) {
            strip.setAll(this.backgroundColor);
            isInitialized = true;
        }//עשיתי את זה כדי שהאיפוס של הצבע של הלוח לא יפריע ללדים של הנחש (הלדים של הנחש כל הזמן התחילו להבהב בגלל האיפוס של צבע הלוח)

        int currentHead = (int) (stopWatch.get() * speed) % totalLeds;

        if (currentHead == lastHead) {
            return;
        }

        if (lastHead != -1) {
            for (int i = 0; i < length; i++) {
                int prevSegment = (lastHead - i + totalLeds) % totalLeds;
                strip.setLed(this.backgroundColor, prevSegment);
            }
        }

        for (int i = 0; i < length; i++) {
            int currentSegment = (currentHead - i + totalLeds) % totalLeds;
            strip.setLed(this.color, currentSegment);
        }

        lastHead = currentHead;

        strip.apply();
    }
}