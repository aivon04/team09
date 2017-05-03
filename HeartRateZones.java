package ca.uwo.csd.cs2212.team09;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**Used for getting the users heart rate zones
 * @author Team 9
 *         
 */
public class HeartRateZones {

    private Double caloriesOut;
    private int minutes;
    private String name;

    /**
     * Constructor for each heart rate zone
     *
     * @param zoneNum The index at which the heart rate zone is in the JSON Array
     * @param date    Determines what date to get the data from
     * @param canned  true if using test data
     */
    public HeartRateZones(Double cals, String namea, int mins, boolean canned) {
    	caloriesOut = cals;
        minutes = mins;
        name = namea;
        if (canned) {
        	Random ran = new Random();
            caloriesOut = Double.parseDouble(ran.nextInt(2000)+"");
            minutes = 5 + ran.nextInt(60);
            
            return;
        } 
    }

    /**
     * Gets calories burned in this zone
     * @return calories burnt in this zone
     */
    public Double getCaloriesOut() {
        return caloriesOut;
    }

    /**
     * Gets minutes spent in this zone
     * @return minutes spent in this zone
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Gets name of this zone
     * @return name of this zone
     */
    public String getName() {
        return name;
    }
}
