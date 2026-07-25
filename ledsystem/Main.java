package ledsystem;

import java.awt.Color;
import ledsystem.ledssim.LedSim;
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        LedSim ledSim = LedSim.createRows(100);

        LedController controller = new LedController(ledSim);
        controller.setAnimationForState(RobotState.IDLE, new SolidAnimation(Color.GREEN));
        controller.setAnimationForState(RobotState.COLLECTING, new SnakeAnimation(Color.RED, Color.BLACK, 3, 5));
        controller.setAnimationForState(RobotState.SCORE_SUCCESS, new BlinkAnimation(Color.BLUE));
        controller.setAnimationForState(RobotState.DISABLED, new SolidAnimation(Color.RED));

        System.out.println("Robot Status: IDLE");
        controller.playState(RobotState.IDLE);
       
        System.out.println("Robot Status: COLLECTING");
        controller.playState(RobotState.COLLECTING);

        System.out.println("Robot Status: SCORE_SUCCESS!");
        controller.playState(RobotState.SCORE_SUCCESS);

        System.out.println("Robot Status: DISABLED");
        controller.playState(RobotState.DISABLED);
        
        long totalRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Program ended. Total runtime: " + totalRuntime + " ms");
    }
}
        
        

        

