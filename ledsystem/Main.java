package ledsystem;
import java.awt.Color;
import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LedSim ledSim = LedSim.createRows(100);

        LedController controller = new LedController(ledSim);
        controller.addAnimation(new SolidAnimation(Color.RED));
        controller.play();

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
