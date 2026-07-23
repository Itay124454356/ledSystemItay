package ledsystem.ledssim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LedSim extends LedStrip {
    private final Point led_positions[];
    private JFrame frame;
    private JPanel panel;

    private static final int LEDS_PER_ROW = 10; // Number of LEDs per row for visualization
    private static final int LED_SIZE = 40; // Size of each LED square
    private static final int GAP = 10; // Gap between LEDs

    public LedSim(Point led_positions[]) {
        super(led_positions.length);
        this.led_positions = led_positions;

        initializeCanvas();
    }

    public static LedSim createRows(int led_count) {
        Point[] poses = new Point[led_count];

        for (int i = 0; i < led_count; i++) {
            int row = i / LEDS_PER_ROW;
            int col = i % LEDS_PER_ROW;
            int x = col * (LED_SIZE + GAP);
            int y = 20 + row * (LED_SIZE + GAP);
            poses[i] = new Point(x, y);
        }

        return new LedSim(poses);
    }

    public static LedSim createSquare(int width, int height) {
        int led_count = width * 2 + height * 2 - 4; // accounting for corners
        Point[] poses = new Point[led_count];
        Point p = null;

        for (int i = 0; i < led_count; i++) {
            if (i < width) { // Top edge: (0,0) to (width-1, 0)
                p = new Point(i, 0);
            } else if (i < width + height - 1) { // Right edge: (width-1,1) to (width-1, height-1)
                p = new Point(width - 1, i - width + 1);
            } else if (i < 2 * width + height - 2) { // Bottom edge: (width-2, height-1) to (0, height-1)
                p = new Point((2 * width + height - 3) - i, height - 1);
            } else { // Left edge: (0, height-2) to (0,1)
                p = new Point(0, (led_count - i));
            }
            p.x *= (LED_SIZE + GAP);
            p.y *= (LED_SIZE + GAP);
            poses[i] = p;
        }

        return new LedSim(poses);
    }

    private void initializeCanvas() {
        frame = new JFrame("LED Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < led_count; i++) {
                    g.setColor(led_colors[i]);
                    g.fillRect(led_positions[i].x, led_positions[i].y, LED_SIZE, LED_SIZE);
                    g.setColor(Color.GRAY);
                    g.drawRect(led_positions[i].x, led_positions[i].y, LED_SIZE, LED_SIZE);
                }
            }
        };

        int maxX = led_positions[0].x;
        int maxY = led_positions[0].y;

        for (Point p : led_positions) {
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }

        panel.setPreferredSize(new Dimension(maxX + LED_SIZE, maxY + LED_SIZE));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void apply() {
        panel.repaint();
    }
}
