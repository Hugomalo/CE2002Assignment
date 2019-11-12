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
        char choice;
        do {
            System.out.println("How many tickets do you want to book ?");
            ticketNb = sc.nextInt();
            if (ticketNb > 10 || ticketNb < 1){
                System.out.println("Please input a number of tickets between 1 and 10.");
            }
        }while(ticketNb > 10 || ticketNb < 1);
        do {
            System.out.println("Are theses tickets for the same showtime and movie ? Hit Y for yes or N for no");
            choice = sc.nextLine().charAt(0);
            if (choice == 'Y'){
                int cineplex;
                do {
                    System.out.println("To select the cinema you want to display showtimes, please select the cineplex first :");
                    CineplexListing.showCineplexes();
                    cineplex = sc.nextInt();
                    sc.nextLine();
                    if (cineplex > 0 && cineplex < CineplexListing.getNbOfCineplexes()) {
                        int cinema;
                        System.out.println("Please choose the cinema :");
                    }
                    else {
                        System.out.println("Please input valid entry");
                    }
                }while (cineplex > 0 && cineplex < CineplexListing.getNbOfCineplexes());
            }
            else if (choice == 'N'){
                for (int i=0; i<ticketNb; i++){
                    Ticket t = new Ticket();
                    //t.book();
                    this.tickets.add(t);
                }
            }
            else{
                System.out.println("Please input a valid entry");
            }
        }while(choice != 'Y' || choice != 'N');

    }
}
