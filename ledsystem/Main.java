package ledsystem;
import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LedSim ledSim = LedSim.createRows(100);

        LedController controller = new LedController(ledSim);
        controller.addAnimation(new BlinkAnimation(null)); 
        controller.play();

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
