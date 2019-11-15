package user;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendar {
    private static ArrayList<LocalDateTime> SunPH; //Stores sundays and public holidays in an Array

    public static ArrayList<LocalDateTime> getSunPH(){
        return SunPH;
    }
    
    public static boolean isPH(LocalDateTime showtime) {
    	boolean isPH = false;
    	for (LocalDateTime time : SunPH) {
    		if (time.getDayOfYear() == showtime.getDayOfYear()) {
    			isPH = true;
    		}
    		else if (showtime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
    			isPH = true;
    		}
    		else {
    			isPH = false;
    		}
    	}
    	return isPH;
    }
 
}
