package user;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendar {
    private static ArrayList<LocalDateTime> SunPH; //Stores sundays and public holidays in an Array

    public ArrayList<LocalDateTime> getSunPH(){
        return SunPH;
    }
}
