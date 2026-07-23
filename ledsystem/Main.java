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
        controller.addAnimation(new SnakeAnimation(Color.GREEN, Color.BLACK, 2.0, 5), 10); //נחש ירוק על רקע שחור במהירות של 2 לדים בשניה וגודלו 5 לדים. 
        controller.play();
        
        
        
        

        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
