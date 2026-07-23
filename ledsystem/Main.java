package ledsystem;

import java.awt.Color;

import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Initialize the LED simulation with a row layout
        LedSim ledSim = LedSim.createRows(100);

        // Set all LEDs to a specific color
        ledSim.setAll(Color.CYAN);

        // Set a multiple LEDs to different colors
        ledSim.setRange(Color.ORANGE, 20, 40);;

        // Set one LED to red
        ledSim.setLed(Color.RED, 5);

        // Apply the changes to the simulation
        ledSim.apply();

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
