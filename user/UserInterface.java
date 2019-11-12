package user;

import admin.CineplexListing;
import admin.Movie;
import admin.MovieListing;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static ArrayList<Booking> bookings;

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
            sc.nextLine();
            switch (choice){
                case 1:{
                    int subChoice;
                    do {
                        System.out.println("Do you want to see all movies (including not showing) ? Hit 1");
                        System.out.println("Do you want to see top 5 movies ranking by ticket sales ? Hit 2");
                        System.out.println("Do you want to see top 5 movies ranking by overall reviews ? Hit 3");
                        System.out.println("Hit 0 to go back to main");
                        subChoice = sc.nextInt();
                        sc.nextLine(); // to avoid skipping of next sc instruction
                        switch (subChoice){
                            case 0:{
                                break;
                            }
                            case 1:{
                                MovieListing.showMovies();
                                break;
                            }
                            case 2:{
                                MovieListing.showRanking(false);
                                break;
                            }
                            case 3:{
                                MovieListing.showRanking(true);
                                break;
                            }
                            default:{
                                System.out.println("Please input a valid entry");
                                break;
                            }
                        }
                    }while (subChoice != 0);
                    break;
                }
                case 2:{
                    int subChoice;
                    do{
                        System.out.println("Hit 0 to go back to main");
                        System.out.println("To select the cinema you want to display showtimes, please select the cineplex first :");
                        CineplexListing.showCineplexes();
                        subChoice = sc.nextInt();
                        sc.nextLine();
                        if (subChoice == 0){
                            break;
                        }
                        else if (subChoice > 0 && subChoice < CineplexListing.getNbOfCineplexes()) {
                            CineplexListing.showShowtimes(subChoice - 1);
                            break;
                        }
                        else{
                            System.out.println("Please input valid entry");
                        }
                    }while (true);
                    break;
                }
                case 3:{
                    String subChoice;
                    System.out.println("Hit 0 to go back to main");
                    System.out.println("Please input the name used for your order");
                    subChoice = sc.nextLine();
                    if (subChoice.equals("0")){
                    }
                    else{
                        Booking.seeBookings(subChoice);
                    }
                    break;
                }
                case 4:{
                    int subChoice;
                    do{
                        System.out.println("Do you want to enter in booking mode ? Hit 1 for Yes, Hit 0 to go back to main");
                        subChoice = sc.nextInt();
                        sc.nextLine();
                        if(subChoice == 0){
                            break;
                        }
                        else if (subChoice == 1){
                            Booking.book();
                            break;
                        }
                        else{
                            System.out.println("Please input a valid entry");
                        }
                    }while(true);
                    break;
                }
                case 5:{
                    break;
                }
                default:{
                    System.out.println("Invalid entry, please try again");
                    break;
                }
            }
        }
    }
}
