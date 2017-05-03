package ca.uwo.csd.cs2212.team09;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.*;

/** Implements the details of the accolades
 * @author team 09
 *
 */
public class Accolades {

    private AccAchievement DailyDistance = new AccAchievement();
    private AccAchievement DailyCalories = new AccAchievement();
    private AccAchievement DailyFloors = new AccAchievement();
    private AccAchievement DailySteps = new AccAchievement();
    private AccAchievement MotorVated = new AccAchievement();
    private AccAchievement DayOff = new AccAchievement();

    private AccAchievement LifeDistance = new AccAchievement();
    private AccAchievement LifeCalories = new AccAchievement();
    private AccAchievement LifeFloors = new AccAchievement();
    private AccAchievement LifeSteps = new AccAchievement();
    private AccAchievement MiddleMan = new AccAchievement();
    private AccAchievement ImmovableObject = new AccAchievement();

    /** Constructor for the accolades to be displayed
     * @param data array of user data
     * @param ltData array of lifetime data
     */
    public Accolades(double data[], String ltData[]) {

        double Ldistance = Double.parseDouble(ltData[6]);
        double Lcalories = Double.parseDouble(ltData[9]);
        double Lfloors = Double.parseDouble(ltData[7]);
        double Lsteps = Double.parseDouble(ltData[8]);

        double Ddistance = data[1];
        double Dcalories = data[0];
        double Dfloors = data[2];
        double Dsteps = data[3];
        double SedentaryM = data[5];
        double Very_ActiveM = data[8];
        double Fairly_ActiveM = data[7];
        double Lightly_ActiveM = data[6];


        /*------------------------------------------------------------*/

         DailyDistance.setDaily(true);
         DailyCalories.setDaily(true);
         DailyFloors.setDaily(true);
         DailySteps.setDaily(true);
         MotorVated.setDaily(true);
         DayOff.setDaily(true);

         LifeDistance.setLifetime(true);
         LifeCalories.setLifetime(true);
         LifeFloors.setLifetime(true);
         LifeSteps.setLifetime(true);
         MiddleMan.setLifetime(true);
         ImmovableObject.setLifetime(true);

        //Daily Accolades are calculated here
        /*------------------------------------------------------------------------*/
        if (Ddistance >= 15) {
            if (!DailyDistance.isTier3()) {
                DailyDistance.setTier3(true);
            }
            if (!DailyDistance.isTier2()) {
                DailyDistance.setTier2(true);
            }
            if (!DailyDistance.isTier1()) {
                DailyDistance.setTier1(true);
            }
        } else if (Ddistance >= 7) {
            if (!DailyDistance.isTier2()) {
                DailyDistance.setTier2(true);
            }
            if (!DailyDistance.isTier1()) {
                DailyDistance.setTier1(true);
            }
        } else if (Ddistance >= 2) {
            if (!DailyDistance.isTier1()) {
                DailyDistance.setTier1(true);
            }
        }
        
        /*--------------------------------------------------------------*/
        if (Dcalories >= 3000) {
            if (!DailyCalories.isTier3() ) {
                DailyCalories.setTier3(true);
            }
            if (!DailyCalories.isTier2() ) {
                DailyCalories.setTier2(true);
            }
            if (!DailyCalories.isTier1() ) {
                DailyCalories.setTier1(true);
            }
        } else if (Dcalories >= 2500) {
            if (!DailyCalories.isTier2() ) {
                DailyCalories.setTier2(true);
            }
            if (!DailyCalories.isTier1() ) {
                DailyCalories.setTier1(true);
            }
        } else if (Dcalories >= 2000) {
            if (!DailyCalories.isTier1() ) {
                DailyCalories.setTier1(true);
            }
        }
        /*--------------------------------------------------------------*/
        if (Dfloors >= 25) {
            if (!DailyFloors.isTier3() ) {
                DailyFloors.setTier3(true);
            }
            if (!DailyFloors.isTier2() ) {
                DailyFloors.setTier2(true);
            }
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        } else if (Dfloors >= 15) {
            if (!DailyFloors.isTier2() ) {
                DailyFloors.setTier2(true);
            }
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        } else if (Dfloors >= 5) {
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        }
        
        /*--------------------------------------------------------------*/
        if (Dfloors >= 25) {
            if (!DailyFloors.isTier3() ) {
                DailyFloors.setTier3(true);
            }
            if (!DailyFloors.isTier2() ) {
                DailyFloors.setTier2(true);
            }
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        } else if (Dfloors >= 15) {
            if (!DailyFloors.isTier2() ) {
                DailyFloors.setTier2(true);
            }
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        } else if (Dfloors >= 5) {
            if (!DailyFloors.isTier1() ) {
                DailyFloors.setTier1(true);
            }
        }
        
        /*--------------------------------------------------------------*/
        if (Dsteps >= 3500) {
            if (!DailySteps.isTier3() ) {
                DailySteps.setTier3(true);
            }
            if (!DailySteps.isTier2() ) {
                DailySteps.setTier2(true);
            }
            if (!DailySteps.isTier1() ) {
                DailySteps.setTier1(true);
            }
        } else if (Dsteps >= 2500) {
            if (!DailySteps.isTier2() ) {
                DailySteps.setTier2(true);
            }
            if (!DailySteps.isTier1() ) {
                DailySteps.setTier1(true);
            }
        } else if (Dsteps >= 1000) {
            if (!DailySteps.isTier1() ) {
                DailySteps.setTier1(true);
            }
        }

        /*--------------------------------------------------------------*/
        if (Very_ActiveM > Lightly_ActiveM) {
            if (!MotorVated.isTier1() ) {
                MotorVated.setTier1(true);
            }
        }
        /*--------------------------------------------------------------*/
        if(SedentaryM >= 240) {
            if (!DayOff.isTier1() ) {
                DayOff.setTier1(true);
            }
        }
/*--------------------------------------------------------------------------------------------*/

        //Lifetime Accolades are calculated here

        if (Ldistance >= 1000000) {
            if (!LifeDistance.isTier3() ) {
                LifeDistance.setTier3(true);
            }
            if (!LifeDistance.isTier2() ) {
                LifeDistance.setTier2(true);
            }
            if (!LifeDistance.isTier1() ) {
                LifeDistance.setTier1(true);
            }
        } else if (Ldistance >= 750000) {
            if (!LifeDistance.isTier2() ) {
                LifeDistance.setTier2(true);
            }
            if (!LifeDistance.isTier1() ) {
                LifeDistance.setTier1(true);
            }
        } else if (Ldistance >= 500000) {
            if (!LifeDistance.isTier1() ) {
                LifeDistance.setTier1(true);
            }
        }
        
        /*--------------------------------------------------------------*/
        if (Lcalories >= 1500000) {
            if (!LifeCalories.isTier3() ) {
                LifeCalories.setTier3(true);
            }
            if (!LifeCalories.isTier2() ) {
                LifeCalories.setTier2(true);
            }
            if (!LifeCalories.isTier1() ) {
                LifeCalories.setTier1(true);
            }
        } else if (Lcalories >= 1000000) {
            if (!LifeCalories.isTier2() ) {
                LifeCalories.setTier2(true);
            }
            if (!LifeCalories.isTier1() ) {
                LifeCalories.setTier1(true);
            }
        } else if (Lcalories >= 750000) {
            if (!LifeCalories.isTier1() ) {
                LifeCalories.setTier1(true);
            }
        }
        /*--------------------------------------------------------------*/
       
        
        /*--------------------------------------------------------------*/
        if (Lfloors >= 600000) {
            if (!LifeFloors.isTier3() ) {
                LifeFloors.setTier3(true);
            }
            if (!LifeFloors.isTier2() ) {
                LifeFloors.setTier2(true);
            }
            if (!LifeFloors.isTier1() ) {
                LifeFloors.setTier1(true);
            }
        } else if (Lfloors >= 300000) {
            if (!LifeFloors.isTier2() ) {
                LifeFloors.setTier2(true);
            }
            if (!LifeFloors.isTier1() ) {
                LifeFloors.setTier1(true);
            }
        } else if (Lfloors >= 100000) {
            if (!LifeFloors.isTier1() ) {
                LifeFloors.setTier1(true);
            }
        }
        
        /*--------------------------------------------------------------*/
        if (Lsteps >= 10000000) {
            if (!LifeSteps.isTier3() ) {
                LifeSteps.setTier3(true);
            }
            if (!LifeSteps.isTier2() ) {
                LifeSteps.setTier2(true);
            }
            if (!LifeSteps.isTier1() ) {
                LifeSteps.setTier1(true);
            }
        } else if (Lsteps >= 7500000) {
            if (!LifeSteps.isTier2() ) {
                LifeSteps.setTier2(true);
            }
            if (!LifeSteps.isTier1() ) {
                LifeSteps.setTier1(true);
            }
        } else if (Lsteps >= 3000000) {
            if (!LifeSteps.isTier1() ) {
                LifeSteps.setTier1(true);
            }
        }
        /*--------------------------------------------------------------*/
        if(Fairly_ActiveM >= 1000000) {
            if (!MiddleMan.isTier1() ) {
                MiddleMan.setTier1(true);
            }
        }
        /*--------------------------------------------------------------*/
        if(SedentaryM >= 2500000) {
            if (!ImmovableObject.isTier1() ) {
                ImmovableObject.setTier1(true);
            }
        }
/*---------------------------------------------------------------------------------*/


    }

    /** Getter method for the daily distance
     * @return the daily distance
     */
    public AccAchievement getDailyDistance() {
        return DailyDistance;
    }

    /** Setter method for the daily distance
     * @param dailyDistance the daily distance to set
     */
    public void setDailyDistance(AccAchievement dailyDistance) {
        DailyDistance = dailyDistance;
    }

    /** Getter method for daily calories
     * @return the daily calories
     */
    public AccAchievement getDailyCalories() {
        return DailyCalories;
    }

    /** Setter method for the daily calories
     * @param dailyCalories the daily calories to set
     */
    public void setDailyCalories(AccAchievement dailyCalories) {
        DailyCalories = dailyCalories;
    }

    /** Getter method for daily floors
     * @return the daily floors
     */
    public AccAchievement getDailyFloors() {
        return DailyFloors;
    }

    /** Setter method for the daily floors
     * @param dailyFloors the daily floors to set
     */
    public void setDailyFloors(AccAchievement dailyFloors) {
        DailyFloors = dailyFloors;
    }

    /** Getter method for the daily steps
     * @return the daily steps
     */
    public AccAchievement getDailySteps() {
        return DailySteps;
    }

    /** Setter method for the daily steps
     * @param dailySteps the daily steps to be set
     */
    public void setDailySteps(AccAchievement dailySteps) {
        DailySteps = dailySteps;
    }

    /** Returns the accolade achievement
     * @return accolade achievement
     */
    public AccAchievement getMotorVated() {
        return MotorVated;
    }

    /** Sets the accolade achievement
     * @param motorVated the accolade achievement to be set
     */
    public void setMotorVated(AccAchievement motorVated) {
        MotorVated = motorVated;
    }

    /** Getter method for DayOff
     * @return the Dayoff
     */
    public AccAchievement getDayOff() {
        return DayOff;
    }

    /** Setter method for DayOff
     * @param dayOff the DayOff to be set
     */
    public void setDayOff(AccAchievement dayOff) {
        DayOff = dayOff;
    }

    /** Getter method for lifetime distance
     * @return the lifetime distance
     */
    public AccAchievement getLifeDistance() {
        return LifeDistance;
    }

    /** Setter method for lifetime distance
     * @param lifeDistance lifetime distance to set
     */
    public void setLifeDistance(AccAchievement lifeDistance) {
        LifeDistance = lifeDistance;
    }

    /** Getter method for lifetime calories
     * @return the lifetime calories
     */
    public AccAchievement getLifeCalories() {
        return LifeCalories;
    }
    
    /** Setter method for lifetime calories
     * @param lifeDistance lifetime calories to set
     */
    public void setLifeCalories(AccAchievement lifeCalories) {
        LifeCalories = lifeCalories;
    }

    /** Getter method for lifetime floors
     * @return lifetime floors
     */
    public AccAchievement getLifeFloors() {
        return LifeFloors;
    }

    /** Setter method for lifetime floors
     * @return lifetime floors to be set
     */
    public void setLifeFloors(AccAchievement lifeFloors) {
        LifeFloors = lifeFloors;
    }

    /** Getter method for lifetime steps
     * @return lifetime steps
     */
    public AccAchievement getLifeSteps() {
        return LifeSteps;
    }

    /** Setter method for lifetime steps
     * @param lifeSteps lifetime steps to be set
     */
    public void setLifeSteps(AccAchievement lifeSteps) {
        LifeSteps = lifeSteps;
    }

    /** Getter method for the MiddleMan
     * @return the MiddleMan
     */
    public AccAchievement getMiddleMan() {
        return MiddleMan;
    }

    /** Setter method for the MiddleMan
     * @param middleMan the MiddleMan to be set
     */
    public void setMiddleMan(AccAchievement middleMan) {
        MiddleMan = middleMan;
    }

    /** Getter method for the ImmovableObject
     * @return the ImmovableObject
     */
    public AccAchievement getImmovableObject() {
        return ImmovableObject;
    }

    /** Setter method for the ImmovableObject
     * @param immovableObject the ImmovableObject to be set
     */
    public void setImmovableObject(AccAchievement immovableObject) {
        ImmovableObject = immovableObject;
    }

    /** Getter method for the achievements
     * @return an array of achievements
     */
    public AccAchievement[] getAchievements(){

        AccAchievement[] Achievements = new AccAchievement[12];
        Achievements[0] = DailyDistance;
        Achievements[1] = DailyCalories;
        Achievements[2] = DailyFloors;
        Achievements[3] = DailySteps;
        Achievements[4] = MotorVated;
        Achievements[5] = DayOff;
        Achievements[6] = LifeDistance;
        Achievements[7] = LifeCalories;
        Achievements[8] = LifeFloors;
        Achievements[9] = LifeSteps;
        Achievements[10] = ImmovableObject;
        Achievements[11] = MiddleMan;

        return Achievements;
    }

}
