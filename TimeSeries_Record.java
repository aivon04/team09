package ca.uwo.csd.cs2212.team09;

/** Record of the TimeSeries
 * @author Team 09
 *
 */
public class TimeSeries_Record {
	private int steps;
	private double calories;
	private double distance;
	private int hr;
	private String time;
	
	
	/**
	 * Default constructor of the TimeSeries_Record
	 * @param stepV steps taken
	 * @param calV calories burned
	 * @param disV distance travelled
	 * @param hrV heart rate
	 * @param timeV time 
	 */
	public TimeSeries_Record(int stepV, double calV, double disV, int hrV, String timeV) {
		steps = stepV;
		calories = calV;
		distance = disV;
		hr = hrV;
		time = timeV;
	}

	/** Retrieves the steps
	 * @return the steps taken
	 */
	public int getSteps() {
		return steps;
	}
	
	/** Retrieves the calories
	 * @return the calories burned
	 */
	public double getCalories() {
		return calories;
	}
	
	/** Retrieves the distance 
	 * @return distance travelled
	 */
	public double getDistance() {
		return distance;
	}
	
	/** Retrieves the heart rate
	 * @return the heart rate
	 */
	public int getHr() {
		return hr;
	}
	
	/** Retrieves the time
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
}
