package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LedSim ledSim = LedSim.createRows(100);

        SolidAnimation solidAnimation = new SolidAnimation(Color.BLUE);
        solidAnimation.apply(ledSim);

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
