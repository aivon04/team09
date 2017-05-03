package ca.uwo.csd.cs2212.team09;

import java.awt.EventQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** This is the main class that runs the program
 * @author Team 9
 *
 */
public class App {
    static Logger logger = LogManager.getLogger(App.class.getName());

    /** Main method that launches the User Interface
     * @param args Used to test program. Use no argument to run using API.
     * Use "test" to launch software test mode using canned data (launches GUI).
     */
    public static void main(String[] args) {
        System.out.println("Starting session");

        //To run this: (uncanned data)
        //java -jar target/team09_FitBitProject-1.0-SNAPSHOT-jar-with-dependencies.jar
        if (args.length == 0){
        	EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					MainView window = new MainView(false);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
        }
        //To run using canned data and launch dashboard, use argument test
        //java -jar target/team09_FitBitProject-1.0-STAGE2-jar-with-dependencies.jar test
        else if (args[0].equals("test")) {
        	System.out.println("Software test mode.");
        	EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					MainView window = new MainView(true);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
        }
        logger.trace("Exiting main");
    }
}
