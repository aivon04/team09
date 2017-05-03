package ca.uwo.csd.cs2212.team09;

import java.io.Serializable;

/** Implements accolades
 * @author Team 09
 *
 */
public class AccAchievement implements Serializable {

    private boolean Daily;
    private boolean Lifetime;
    private boolean tier1 = false;
    private boolean tier2 = false;
    private boolean tier3 = false;



    /**
     * Default constructor for the accolade
     */
    public AccAchievement(){

    }


    /** Returns whether the accolade is daily vs. lifetime
     * @return true if the accolade is daily
     */
    public boolean isDaily() {return Daily;}

    /** Sets whether the accolade is daily vs. lifetime
     * @param daily
     */
    public void setDaily(boolean daily) {Daily = daily;}

    /** Returns whether the accolade is lifetime vs. daily
     * @return true if the accolade is lifetime
     */
    public boolean isLifetime() {return Lifetime;}

    /** Sets whether the accolade is lifetime vs. daily
     * @param lifetime
     */
    public void setLifetime(boolean lifetime) {Lifetime = lifetime;}

    /** Returns whether the accolade is tier 3
     * @return True if accolade is tier 3
     */
    public boolean isTier3() {
        return tier3;
    }

    /** Sets true/false value if the accolade is tier3
     * @param tier3 true if accolade is tier 3
     */
    public void setTier3(boolean tier3) {
        this.tier3 = tier3;
    }

    /** Returns whether the accolade is tier 2
     * @return True if accolade is tier 2
     */
    public boolean isTier2() {
        return tier2;
    }

    /** Sets true/false value if the accolade is tier2
     * @param tier2 true if accolade is tier 2
     */
    public void setTier2(boolean tier2) {
        this.tier2 = tier2;
    }

    /** Returns whether the accolade is tier 1
     * @return True if accolade is tier 1
     */
    public boolean isTier1() {
        return tier1;
    }

    /** Sets true/false value if the accolade is tier1
     * @param tier1 true if accolade is tier 1
     */
    public void setTier1(boolean tier1) {
        this.tier1 = tier1;
    }


}
