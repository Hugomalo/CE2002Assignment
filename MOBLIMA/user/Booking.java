package MOBLIMA.user;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String filepath = "listing" + File.separator + "bookings.ser";

    static String getFilepath() {
        return filepath;
    }

    private String name;
    private String phoneNb;
    private String email;
    private String TID;
    private ArrayList<Ticket> tickets;
    private Float price;

    Booking(String name, String phoneNb, String email, String cineCode, ArrayList<Ticket> tickets) {
        this.name = name;
        this.phoneNb = phoneNb;
        this.email = email;
        this.tickets = tickets;
        this.TID = SetTID(cineCode);
        Float pr = 0.f;
        for (Ticket t : tickets){
            pr += t.getPrice();
        }
        price = pr;
    }


    private String SetTID(String c){
        LocalDateTime d = LocalDateTime.now();
        return String.format("%s%s%s%d%d%d", c, String.valueOf(d.getYear()), d.getMonthValue(), d.getDayOfMonth(), d.getHour(), d.getMinute());
    }

    String getName(){
        return name;
    }

    void showBooking(){
        System.out.println("Transaction ID: " + TID);
        System.out.println("Phone number: " + phoneNb);
        System.out.println("email: " + email);
        System.out.println("Total Price: " + price + "S$");
        System.out.println("Tickets :");
        for (Ticket t : tickets){
            t.showTicket();
        }
    }
}
