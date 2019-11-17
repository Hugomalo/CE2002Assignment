package MOBLIMA.user;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Calendar class for calendar related functions such as
 * adding a public holiday and checking whether a certain
 * day falls on a Sunday or a public holiday.
 * @author CE2002 SE3 Group 4
 */

public class Calendar {
    private static ArrayList<LocalDateTime> SunPH = new ArrayList<LocalDateTime>(); //Stores sundays and public holidays in an Array

    /**
     * Gets a list of Sundays and public holidays.
     * @return List of Sundays and public holidays
     */
    public static ArrayList<LocalDateTime> getSunPH(){
        return SunPH;
    }
    
    /**
     * Checks whether a showtime falls on a Sunday/public holiday.
     * @param showtime Showtime
     * @return Boolean result
     */
    public static boolean isPH(LocalDateTime showtime) {
    	boolean isPH = false;
    	for (LocalDateTime time : SunPH) {
    		if ((time.getDayOfYear() == showtime.getDayOfYear()) || (showtime.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
    			isPH = true;
    		}
    		else {
    			isPH = false;
    		}
    	}
    	return isPH;
    }

    /**
     * A list of all current public holidays until 2020.
     */
    public static void initSunPH(){

        LocalDateTime christmas = LocalDateTime.of(2019,12,25,0,0);
        SunPH.add(christmas);
        LocalDateTime newYears = LocalDateTime.of(2020,1,1,0,0);
        SunPH.add(newYears);
        LocalDateTime chineseNY1 = LocalDateTime.of(2020,1,25,0,0);
        SunPH.add(chineseNY1);
        LocalDateTime chineseNY2 = LocalDateTime.of(2020,1,26,0,0);
        SunPH.add(chineseNY2);
        LocalDateTime goodFriday= LocalDateTime.of(2020,4,10,0,0);
        SunPH.add(goodFriday);
        LocalDateTime labourDay= LocalDateTime.of(2020,5,11,0,0);
        SunPH.add(labourDay);
        LocalDateTime vesakDay= LocalDateTime.of(2020,5,7,0,0);
        SunPH.add(vesakDay);
        LocalDateTime hariRayaP= LocalDateTime.of(2020,5,24,0,0);
        SunPH.add(hariRayaP);
        LocalDateTime hariRayaH= LocalDateTime.of(2020,7,31,0,0);
        SunPH.add(hariRayaH);
        LocalDateTime nationalDay= LocalDateTime.of(2020,8,9,0,0);
        SunPH.add(nationalDay);
        LocalDateTime deepavali= LocalDateTime.of(2020,11,14,0,0);
        SunPH.add(deepavali);
        LocalDateTime christmas2= LocalDateTime.of(2020,12,25,0,0);
        SunPH.add(christmas2);
    }

    /**
     * Adds a public holiday to the program's calendar.
     * @param month Month
     * @param day Day
     */
    public static void addPH(int month, int day){
        LocalDateTime newPH = LocalDateTime.of(LocalDateTime.now().getYear(),month,day,0,0);
        SunPH.add(newPH);
    }
}
