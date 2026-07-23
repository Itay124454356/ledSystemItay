package ledsystem.ledssim;

import java.awt.Color;

public abstract class LedStrip {
    protected final int led_count;
    protected final Color[] led_colors;

    public LedStrip(int led_count) {
        this.led_count = led_count;
        this.led_colors = new Color[led_count];
        for (int i = 0; i < led_count; i++) {
            led_colors[i] = Color.BLACK; // default color
        }
    }

    public void setLed(Color color, int index) {
        if (index < 0 || index >= led_count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        led_colors[index] = color;
    }

    public void setLeds(Color[] colors) {
        if (colors.length != led_count) {
            throw new IllegalArgumentException("Colors array length must match number of LEDs: " + led_count);
        }
        System.arraycopy(colors, 0, led_colors, 0, led_count);
    }

    public void setRange(Color color, int start, int end) {
        for (int i = start; i < end; i++) {
            led_colors[i] = color;
        }
    }
  
    public void setAll(Color color) {
        setRange(color, 0, led_count);
    }

    public Color getLed(int index) {
        if (index < 0 || index >= led_count) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return led_colors[index];
    }

    /**
     * Applies the LED colors to the strip.
     */
    public abstract void apply();

    public int getLedCount() {
        return led_count;
    }
}
