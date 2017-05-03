package ca.uwo.csd.cs2212.team09;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** Class used to store and manipulate the data of the user
 * @author Team 9
 *
 */
public class UserData {

    private int bestSteps = -1;
    private int bestFloors = -1;
    private int bestCalories = -1;
    private int bestDistance = -1;

    private int totalSteps = -1;
    private int totalFloors = -1;
    private int totalCalories = -1;
    private int totalDistance = -1;

    /** Refreshes the user's data
     * @param canned true if using canned/test data
     * @param date date of the data retrieved in the format "yyyy-mm-dd"
     * @return An array of strings storing the user's data
     */
    public double[] refreshAll(boolean canned, String date) {
        double[] returnData = new double[9];
        if (canned == true) {
            String[] tempSplit = date.split("-");
            int genVal = Integer.parseInt(tempSplit[0]) + Integer.parseInt(tempSplit[1]) + Integer.parseInt(tempSplit[2]);
            System.out.println("our hash is: " + genVal);

            genVal = genVal % 31;

            //Use mod 31 to generate different values
            returnData[0] = 1000.0 + (genVal * 50);
            returnData[1] = 12.0 + (genVal * 2);
            returnData[2] = 3.0 + (genVal);
            returnData[3] = 2378.0 + (480 * (genVal / 3)) + genVal;
            returnData[4] = 54.0 + (genVal * 14);
            returnData[5] = 1440.0 - returnData[4];
            return returnData;
        }
        Request getData = new Request();
        try {
            //final JSONObject obj = new JSONObject(getData.requestFor("activities/date/2016-01-08.json"));
            final JSONObject obj = new JSONObject(getData.requestFor("activities/date/" + date + ".json"));

            final JSONObject fitData = obj.getJSONObject("summary");
            //System.out.println("steps: " + fitData.getString("steps"));
            returnData[3] = (fitData.getDouble("steps"));

            final JSONArray distanceArray = fitData.getJSONArray("distances");
            final JSONObject totalDistance = distanceArray.getJSONObject(0);
            //System.out.println("distance: " + totalDistance.getString("distance"));
            returnData[1] = (totalDistance.getDouble("distance"));

            //System.out.println("floors: " + fitData.getDouble("floors"));
            returnData[2] = (fitData.getDouble("floors"));

            //System.out.println("calories: " + fitData.getDouble("caloriesOut"));
            returnData[0] = (fitData.getDouble("caloriesOut"));

            double activeMinutes = 0;
            activeMinutes += Integer.parseInt(fitData.getString("fairlyActiveMinutes"));
            activeMinutes += Integer.parseInt(fitData.getString("lightlyActiveMinutes"));
            activeMinutes += Integer.parseInt(fitData.getString("veryActiveMinutes"));
            //System.out.println("active minutes: " + activeMinutes);
            returnData[4] = activeMinutes;

            //System.out.println("sedentary minutes: " + fitData.getString("sedentaryMinutes"));
            returnData[5] = (fitData.getDouble("sedentaryMinutes"));

            returnData[6] = Integer.parseInt(fitData.getString("lightlyActiveMinutes"));
            returnData[7] = Integer.parseInt(fitData.getString("fairlyActiveMinutes"));
            returnData[8] = Integer.parseInt(fitData.getString("veryActiveMinutes"));

        } catch (Exception e) {
            //TODO: Throw an exception
            System.out.println("Failed to refresh all data: " + e.getMessage());
        }
        return returnData;
    }

    /**
     * Refreshes userdata for the mysummary panel
     * @param canned true if using test data
     * @return the userdata
     */
    public String[] refreshMySummary(boolean canned) {
        String[] returnData = new String[10];
        if (canned == true) {
            returnData[0] = "02-25-2016"; //best distance (date)
            returnData[1] = "72";          //best distance
            returnData[2] = "03-20-2016"; //best floors (date)
            returnData[3] = "64";          //best floors
            returnData[4] = "03-01-2016"; //best steps (date)
            returnData[5] = "11304";          //best steps
            returnData[6] = "2073";          //lifetime distance
            returnData[7] = "1803";         //lifetime floors
            returnData[8] = "204372";         //lifetime steps
            returnData[9] = "184574";         //lifetime calories
            return returnData;
        }
        Request getData = new Request();
        try {
            //final JSONObject obj = new JSONObject(getData.requestFor("activities/date/2016-01-08.json"));
            final JSONObject obj = new JSONObject(getData.requestFor("activities.json"));

            final JSONObject fitData = obj.getJSONObject("best");

            final JSONObject trackerData = fitData.getJSONObject("tracker");

            JSONObject bestValue = trackerData.getJSONObject("distance");
            //System.out.println("best distance date: " + bestValue.getString("date"));
            //System.out.println("best distance: " + bestValue.getString("value"));
            returnData[0] = bestValue.getString("date");
            returnData[1] = bestValue.getString("value");

            bestValue = trackerData.getJSONObject("floors"); //TODO: This returns an unrounded double; Fix it by casting to int
            //System.out.println("best floors date: " + bestValue.getString("date"));
            //System.out.println("best floors: " + bestValue.getString("value"));
            returnData[2] = bestValue.getString("date");
            returnData[3] = bestValue.getString("value");

            bestValue = trackerData.getJSONObject("steps");
            //System.out.println("best steps date: " + bestValue.getString("date"));
            //System.out.println("best steps: " + bestValue.getString("value"));
            returnData[4] = bestValue.getString("date");
            returnData[5] = bestValue.getString("value");

            final JSONObject lifeTime = obj.getJSONObject("lifetime");
            bestValue = lifeTime.getJSONObject("total");

            String totalValue = bestValue.getString("distance");
           // System.out.println("total Distance travelled: " + totalValue);
            returnData[6] = (totalValue);

            totalValue = bestValue.getString("floors");
            //System.out.println("total Floors climbed: " + totalValue);
            returnData[7] = (totalValue);

            totalValue = bestValue.getString("steps");
            //System.out.println("total Steps taken: " + totalValue);
            returnData[8] = (totalValue);

            totalValue = bestValue.getString("caloriesOut");
            //System.out.println("total calories: " + totalValue);
            returnData[9] = (totalValue);

        } catch (Exception e) {
            //TODO: Throw an exception
        	String bestnltDate[] = {" ", "0", " ", "0", " ", "0", "0", "0", "0", "0"};
            System.out.println("Failed to refresh all data: " + e.getMessage());
            return bestnltDate;
        }
        return returnData;
    }

    /**
     * Retrieves the steps taken for the User
     * @param canned true if using canned/test data
     * @param date date of the data retrieved in the format "yyyy-mm-dd"
     * @return the number of steps taken
     */
    public int getSteps(boolean canned, String date) {
        if (canned == true) {
            return 1337;
        }

        Request getData = new Request();
        try {
            //Substitute the string in getData.requestFor("STRINGHERE") for what you need
            final JSONObject obj = new JSONObject(getData.requestFor("activities/steps/date/" + date + "/1d.json"));
            final JSONArray fitData = obj.getJSONArray("activities-steps");
            final JSONObject fitAttribute = fitData.getJSONObject(0);
            return fitAttribute.getInt("value");
        } catch (Exception e) {
            System.out.println("Failed to get steps: " + e.getMessage());
        }

        //Something has gone horribly wrong if we reach this point, throw an exception here and let someone else deal with it
        //TODO: Throw an exception
        return -1;
    }

    /** Retrieves the number of floors climbed by the user
     * @param canned true if using canned/test data
     * @param date date of the data retrieved in the format "yyyy-mm-dd"
     * @return the number of floors climbed by the user
     */
    public int getFloors(boolean canned, String date) {
        if (canned == true) {
            return 137;
        }

        Request getData = new Request();
        try {
            final JSONObject obj = new JSONObject(getData.requestFor("activities/floors/date/" + date + "/1d.json"));
            final JSONArray fitData = obj.getJSONArray("activities-floors");
            final JSONObject fitAttribute = fitData.getJSONObject(0);
            return fitAttribute.getInt("value");
            //Something has gone horribly wrong if we reach this point, throw an exception here and let someone else deal with it
        } catch (Exception e) {
            System.out.println("Failed to get floors: " + e.getMessage());
        }
        return -1;
    }

/*    public static Achievements getAchievements(boolean canned){
        //TODO: Complete this method
        return new Achievements();
    }*/

    /**
     * gets the user's resting heartrate
     * @param canned true if using canned/test data
     * @param date date of the data retrieved in the format "yyyy-mm-dd"
     * @return the user's resting heart rate
     * @throws JSONException
     */
    public int getRestingHeartRate(boolean canned, String date) throws JSONException {

        if (canned == true) {
            String[] tempSplit = date.split("-");
            int genVal = Integer.parseInt(tempSplit[0]) + Integer.parseInt(tempSplit[1]) + Integer.parseInt(tempSplit[2]);

            genVal = genVal % 31;
            return 50 + genVal;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (df.format(new Date()).equals(date)) {
        	return 0;
        }
        Request getData = new Request();
        final JSONObject obj = new JSONObject(getData.requestFor("activities/heart/date/"+ date +"/1d.json"));
        final JSONArray fitData = obj.getJSONArray(("activities-heart"));

        final JSONObject fitAttribute = fitData.getJSONObject(0);
        final JSONObject values = fitAttribute.getJSONObject("value");
        //System.out.println(values.toString());
        try {
        	return values.getInt("restingHeartRate");
        }
        catch (Exception e) {
        	return 0;
        }
    }

    /**
     *
     * creates an array of heart rate zones storing each zone
     * because only the default ones are used 0 will store Out of Range, 1 will store Fat Burn, 2 Cardio, 3 Peak
     *
     * @param date determines what day to use
     * @param canned true if using canned/test data
     * @return heart rate zones
     * @throws JSONException
     */
    public  HeartRateZones[] getHeartRateZones (String date, boolean canned) throws JSONException{
        //creates 4 heartrate zones for canned data
        if(canned){
            HeartRateZones[] foo = new HeartRateZones[4];
            for(int i = 0;i<4;i++ ) {
            	String name;
            	if (i == 0) name = "Out of Range";
                else if (i == 1) name = "Fat Burn";
                else if (i == 2) name = "Cardio";
                else name = "Peak";
            	foo[i] = new HeartRateZones(0.0, name, 0, true);
            }
            return foo;
        }
        HeartRateZones ohno = new HeartRateZones(0.0, "Zone", 0 ,canned);
        HeartRateZones[] heartZones = {ohno, ohno, ohno, ohno};
        try {
            Request getData = new Request();
            final JSONObject obj = new JSONObject(getData.requestFor("activities/heart/date/" + date + "/1d.json"));
            final JSONArray fitData = obj.getJSONArray(("activities-heart"));

            final JSONObject fitAttribute = fitData.getJSONObject(0);
            final JSONObject values = fitAttribute.getJSONObject("value");
            final JSONArray zones = values.getJSONArray("heartRateZones");
            // gets the obj element from the array specified by the index number (zoneNum)
            //System.out.println(zones.toString());
            for (int i=0;i<4;i++) {
            	final JSONObject theZone = zones.getJSONObject(i);
            	//System.out.println(theZone.toString());
            	double caloriesOut = 0.0;
            	int minutes = 0;
            	try {
            		caloriesOut = theZone.getDouble("caloriesOut");
            	}
            	catch (Exception e) {
            		
            	}
            	try {
            		minutes = theZone.getInt("minutes");
            	}
            	catch (Exception e) {
            		
            	}
                
                String name = theZone.getString("name");
            	heartZones[i] = new HeartRateZones(caloriesOut, name, minutes,canned);
            }           
        } catch (JSONException e) {
        	//System.out.println(e.getMessage());
            //TODO: Handle this exception
        }
        /*
        Request getData = new Request();
        final JSONObject obj = new JSONObject(getData.requestFor("activities/heart/date/" + date + "/1d.json"));
        final JSONArray fitData = obj.getJSONArray(("activities-heart"));

        final JSONObject fitAttribute = fitData.getJSONObject(0);
        final JSONObject values = fitAttribute.getJSONObject("value");
        final JSONArray zones = values.getJSONArray("heartRateZones");

        HeartRateZones[] heartZones = new HeartRateZones[zones.length()];
        // stores all zones in the heartzones array
        for (int i = 0; i < zones.length(); i++){
            heartZones[i] = new HeartRateZones (i,date,canned);
        }
        */
        return heartZones;
    }
    
    /** Retrieves the TimeSeriesData
     * @param zoomed True if zoomed, false otherwise
     * @param date Date of data to be pulled
     * @param detailLevel Level of detail for the time series data
     * @param startTime Starting time of the time series
     * @param endTime Ending time of the time series
     * @param canned True if using canned data
     * @return
     */
    public TimeSeries_Record[] getTimeSeriesData (boolean zoomed, String date, String detailLevel, String startTime, String endTime, boolean canned) {
    	boolean returnNAData = false;
    	if (!canned) {
    		Request getData = new Request();
    		try {
    			//GET https://api.fitbit.com/1/user/-/[resource-path]/date/[date]/1d/[detail-level]/time/[start-time]/[end-time].json
    			//GET /1/user/-/activities/steps/date/2014-09-01/1d.json  
    			//activities/calories  
    			//activities/steps
    			//activities/distance  
    			//activities/floors
    			String baseReq = "/date/" + date + (!zoomed ? "/1d.json":"/1d/"+detailLevel+"/time/"+startTime+((startTime == null || startTime.length()==0) ? "":"/")+endTime+".json");
    			String baseReqForHR = "/date/" + date + (!zoomed ? "/1d/1min.json":"/1d/1min/time/"+startTime+((startTime == null || startTime.length()==0) ? "":"/")+endTime+".json");
    			//System.out.println("activities/steps" + baseReq);
    			final JSONObject stepObj     = new JSONObject(getData.requestFor("activities/steps" + baseReq));
    			final JSONObject caloriesObj = new JSONObject(getData.requestFor("activities/calories" + baseReq));
    			final JSONObject distanceObj = new JSONObject(getData.requestFor("activities/distance" + baseReq));
    			final JSONObject hrObj       = new JSONObject(getData.requestFor("activities/heart" + baseReqForHR));
    			
                final JSONArray stepData 	 = 	   stepObj.getJSONObject("activities-steps-intraday").getJSONArray("dataset");
                final JSONArray caloriesData = caloriesObj.getJSONObject("activities-calories-intraday").getJSONArray("dataset");
                final JSONArray distanceData = distanceObj.getJSONObject("activities-distance-intraday").getJSONArray("dataset");
                final JSONArray hrData       = 	     hrObj.getJSONObject("activities-heart-intraday").getJSONArray("dataset");
                
                int hrc = 0;
                boolean isHr = hrData.length()==0 ? false:true;
                TimeSeries_Record[] rt = new TimeSeries_Record[stepData.length()];
                for (int i=0;i<stepData.length();i++) {
                	int step = stepData.getJSONObject(i).getInt("value");
                	int calories = caloriesData.getJSONObject(i).getInt("value");
                	double distance = distanceData.getJSONObject(i).getDouble("value");
                	String time = stepData.getJSONObject(i).getString("time");
                	int hr = 0;
                	if (isHr) {
                		if (hrc < hrData.length()) {
                			if (hrData.getJSONObject(hrc).getString("time").equals(time)) {
                				hr = hrData.getJSONObject(hrc).getInt("value");
                				hrc++;
                			}
                		}
                	}
                	
                	rt[i] = new TimeSeries_Record(step, calories, distance, hr, time);
                }
    			
                return rt;
    		}
    		catch (Exception e) {
    			returnNAData = true;
    			System.out.println("An error happened while fetching timeseries data from fitbit.");
    		}
    		finally {
    			
    		}
    	}
    	TimeSeries_Record[] rt = new TimeSeries_Record[(zoomed ? 60:24*4)];
    	Random ran = new Random();
    	if (zoomed) {
    		//hh:mm:ss
    		for (int i=0;i<60;i++) {
    			rt[i] = new TimeSeries_Record(returnNAData ? 1: 800+ran.nextInt(2000),
    											returnNAData ? 2: 200+ran.nextInt(200),
    											returnNAData ? 3: 1+ran.nextInt(3),
    											returnNAData ? 4: 50+ran.nextInt(60),
    										  startTime.substring(0, 2)+":"+(i<10?"0"+i:i)+":00");
    		}
    	}
    	else {
    		for (int i=0;i<24*4;i++) {
    			rt[i] = new TimeSeries_Record(returnNAData ? 1: 800+ran.nextInt(2000),
    											returnNAData ? 2: 200+ran.nextInt(200),
    											returnNAData ? 3: 1+ran.nextInt(3),
    											returnNAData ? 4: 50+ran.nextInt(60),
    										  ((i/4)<10?("0"+(i/4)):(i/4))+":"+(i%4==0?"00":(i%4)*15)+":00");
    		}
    	}
		return rt;
		
    }
    
    /**
    *
    * Creates the Daily Goals from the DailyGoals class constructor
    * @param date date of the daily goals data to be pulled
    * @param canned true if using canned data
    * @return the created Daily Goals
    * @throws JSONException
    */
   public DailyGoals getDailyGoals( String date, boolean canned) throws JSONException{
       DailyGoals goals = new DailyGoals(date, canned);
       return goals;
   }



    /** 
     * Checks if user is at daily goal
     * @param data array of data user currently has accrued (steps, etc.)
     * @param date date of the data to be pulled
     * @param canned Whether canned data is used or not
     * @return an array of strings detailing progress on daily goals.
     * [0] = Calories
     * [1] = Distance
     * [2] = Floors
     * [3] = Steps
     * @throws JSONException
     */
    public String[] isAtGoal(double[] data,String date,Boolean canned) throws JSONException{
        DailyGoals goals = new DailyGoals(date, canned);

        int dCalories = (int) data[0];
        int dDistance = (int) data[1];
        int dFloors = (int) data[2];
        int dSteps = (int) data[3];

        String[] s = new String [4];

        //Calories
        if (dCalories < goals.getCaloriesOutGoal())
            s[0] = "Calories Burned Goal: Below goal";
        else if (dCalories== goals.getCaloriesOutGoal())
            s[0] = "Calories Burned Goal: Reached goal";
        else
            s[0] = "Calories Burned Goal: Surpassed goal";

        //Distance
        if (dDistance < goals.getDistanceGoal())
            s[1] = "Distance Traveled Goal: Below goal";
        else if (dDistance == goals.getDistanceGoal())
            s[1] = "Distance Traveled Goal: Reached goal";
        else
            s[1] = "Distance Traveled Goal: Surpassed goal";

        //Floors
        if (dFloors < goals.getFloorsGoal())
            s[2] = "Floors Climbed Goal: Below goal";
        else if (dFloors == goals.getFloorsGoal())
            s[2] = "Floors Climbed Goal: Reached goal";
        else
            s[2] = "Floors Climbed Goal: Surpassed goal";

        //Steps
        if (dSteps < goals.getStepsGoal())
            s[3] = "Steps Taken Goal: Below goal";
        else if (dSteps == goals.getStepsGoal())
            s[3] = "Steps Taken Goal: Reached goal";
        else
            s[4] = "Steps Taken Goal: Surpassed goal";

        //Return statement
        return s;
    }

}
