package ledsystem;
import java.awt.Color;
import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LedSim ledSim = LedSim.createRows(100);

        LedController controller = new LedController(ledSim);
        controller.addAnimation(new BlinkAnimation(Color.RED), 4); 
        controller.play();
        controller.addAnimation(new SnakeAnimation(Color.GREEN, Color.BLACK, 2.0, 5), 10); //create a SnakeAnimation with green color, black background, speed of 2.0, and length of 5
        controller.play();
        
        
        
        

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
