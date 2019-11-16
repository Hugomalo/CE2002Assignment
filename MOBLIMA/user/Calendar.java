package MOBLIMA.user;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendar {
    private static ArrayList<LocalDateTime> SunPH = new ArrayList<LocalDateTime>(); //Stores sundays and public holidays in an Array

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
}