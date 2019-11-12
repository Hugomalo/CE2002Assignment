package user;

import admin.Cinema;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {
    private String name;
    private int phoneNb;
    private String email;
    private String TID;
    private ArrayList<Ticket> tickets;

    public void SetTID(Cinema c){
        LocalDateTime d = LocalDateTime.now();
        TID = String.format(String.valueOf(d.getYear()), d.getMonth(), d.getDayOfMonth(), d.getHour(), d.getMinute(), c.getCineCode());
    }

    public String getName(){
        return name;
    }

    public static void seeBookings(String n){
    }

    public static void book(){

    }
}
