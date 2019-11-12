package user;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking {
    private String name;
    private int phoneNb;
    private String email;
    private String TID;
    private ArrayList<Ticket> tickets;

    public Booking(String name, int phoneNb, String email, String cineCode, ArrayList<Ticket> tickets) {
        this.name = name;
        this.phoneNb = phoneNb;
        this.email = email;
        this.tickets = tickets;
        this.TID = SetTID(cineCode);
    }


    public String SetTID(String c){
        LocalDateTime d = LocalDateTime.now();
        String tid = String.format(String.valueOf(d.getYear()), d.getMonth(), d.getDayOfMonth(), d.getHour(), d.getMinute(), c);
        return tid;
    }

    public String getName(){
        return name;
    }

    public static void seeBookings(String n){
    }
}
