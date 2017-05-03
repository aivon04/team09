package ca.uwo.csd.cs2212.team09;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * Implements the Daily Goals of the FitBit Application
 *
 * @author Team 9
 */

public class DailyGoals{

    private int caloriesOutGoal;
    private double distanceGoal;
    private int floorsGoal;
    private int stepsGoal;


    /**
     * A constructor to set the four parameters of the Daily Goal
     *
     * @param date   Determines what date to use
     * @param canned Determines if in test mode
     * @throws JSONException
     */
    public DailyGoals(String date, boolean canned) throws JSONException {
        if (canned) {
            caloriesOutGoal = 750;
            distanceGoal = 100;
            floorsGoal = 200;
            stepsGoal = 2500;
        } else {
            Request getData = new Request();
            final JSONObject obj = new JSONObject(getData.requestFor("activities/date/" + date + ".json"));
            final JSONObject fitData = obj.getJSONObject("goals");

            caloriesOutGoal = fitData.getInt("caloriesOut");
            distanceGoal = fitData.getDouble("distance");
            floorsGoal = fitData.getInt("floors");
            stepsGoal = fitData.getInt("steps");
        }
    }

    /**
     * A constructor for the DailyGoals using default values (750 calories, 100 distance, 200 floors, and 2500 steps)
     */
    public DailyGoals() {
        caloriesOutGoal = 750;
        distanceGoal = 100;
        floorsGoal = 200;
        stepsGoal = 2500;
    }

    /**
     * Retrieves the goal for calories burned
     *
     * @return the goal for calories burned
     */
    public int getCaloriesOutGoal() {
        return caloriesOutGoal;
    }

    /**
     * Sets the goal for calories burned
     *
     * @param caloriesOutGoal New goal for calories to be burned
     */
    public void setCaloriesOutGoal(int caloriesOutGoal) {
        this.caloriesOutGoal = caloriesOutGoal;
    }

    /**
     * Retrieves the goal for distance traveled
     *
     * @return the goal for distance traveled
     */
    public double getDistanceGoal() {
        return distanceGoal;
    }

    /**
     * Sets the goal for distance traveled
     *
     * @param distanceGoal New goal for distance traveled
     */
    public void setDistanceGoal(double distanceGoal) {
        this.distanceGoal = distanceGoal;
    }

    /**
     * Retrieves the goal for floors climbed
     *
     * @return the goal for floors climbed
     */
    public int getFloorsGoal() {
        return floorsGoal;
    }

    /**
     * Sets the goal for floors climbed
     *
     * @param floorsGoal New goal for floors climbed
     */
    public void setFloorsGoal(int floorsGoal) {
        this.floorsGoal = floorsGoal;
    }

    /**
     * Retrieves the goal for steps taken
     *
     * @return the goal of the steps taken
     */
    public int getStepsGoal() {
        return stepsGoal;
    }

    /**
     * Sets the goal for steps taken
     *
     * @param stepsGoal New goal for steps taken
     */
    public void setStepsGoal(int stepsGoal) {
        this.stepsGoal = stepsGoal;
    }
}