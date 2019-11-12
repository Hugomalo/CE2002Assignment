package user;

import admin.Movie;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private ArrayList<Booking> bookings;

    public ArrayList<Booking> getBookings(String n) {
        ArrayList<Booking> usrBookings = new ArrayList<Booking>();
        for (int i=0; i<bookings.size(); i++){
            if (bookings.get(i).getName().equals(n)){
                usrBookings.add(bookings.get(i));
            }
        }
        return usrBookings;
    }

    protected void addReview(Movie m){
        Review r = new Review();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your score for the movie " + m.getTitle() + " ?");
        r.rating = sc.nextInt();
        System.out.println("Please tell us more, what did you think of " + m.getTitle() + " ?");
        r.review = sc.nextLine();
        m.addReview(r);
    }

    public static void mainSwitch(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 5) {
            System.out.println("What action do you want to do ?");
            System.out.println("Hit 1 to display movie listing, hit 2 to display showtimes, hit 3 to view your bookings, hit 4 to book tickets, 5 to quit");
            choice = sc.nextInt();
            switch (choice){
                case 1:{

                }
            }
        }
    }
}
