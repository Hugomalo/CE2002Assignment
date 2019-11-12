package user;

import admin.Cinema;
import admin.CineplexListing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void book(){
        Scanner sc = new Scanner(System.in);
        int ticketNb;
        do {
            System.out.println("How many tickets do you want to book ?");
            ticketNb = sc.nextInt();
            if (ticketNb > 10 || ticketNb < 1){
                System.out.println("Please input a number of tickets between 1 and 10.");
            }
        }while(ticketNb > 10 || ticketNb < 1);
        tickets = Ticket.book(ticketNb);
    }
}
