package ledsystem;
import java.awt.Color;
import ledsystem.ledssim.LedSim;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        LedSim ledSim = LedSim.createRows(100);

        LedController controller = new LedController(ledSim);
        Animation sequenceGroup = new SequentialAnimationGroup(
            5, 
            new SolidAnimation(Color.RED),  
            new SnakeAnimation(Color.GREEN, Color.BLACK, 2, 5),        
            new BlinkAnimation(Color.BLUE)            
        );
        
        controller.addAnimation(sequenceGroup, 20);
        controller.play();
        
        

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
