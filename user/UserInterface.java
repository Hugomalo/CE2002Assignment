package user;

import admin.Movie;
import admin.MovieListing;

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
            sc.nextLine();
            switch (choice){
                case 1:{
                    int subChoice;
                    do {
                        System.out.println("Do you want to see all movies (including not showing) ? Hit 1");
                        System.out.println("Do you want to see top 5 movies ranking by ticket sales ? Hit 2");
                        System.out.println("Do you want to see top 5 movies ranking by overall reviews ? Hit 3");
                        System.out.println("Hit 4 to go back to main");
                        subChoice = sc.nextInt();
                        sc.nextLine(); // to avoid skipping of next sc instruction
                        switch (subChoice){
                            case 1:{
                                MovieListing.showMovies();
                            }
                            case 2:{
                                MovieListing.showRanking(false);
                            }
                            case 3:{
                                MovieListing.showRanking(true);
                            }
                        }
                    }while (subChoice != 4);
                    break;
                }
                case 2:{
                    int subChoice;
                    do{
                        subChoice = sc.nextInt();
                        sc.nextLine();
                    }while (subChoice != 3);
                    break;
                }
                case 3:{
                    int subChoice;
                    do{
                        subChoice = sc.nextInt();
                        sc.nextLine();
                    }while (subChoice != 3);
                    break;
                }
                case 4:{
                    int subChoice;
                    do{
                        subChoice = sc.nextInt();
                        sc.nextLine();
                    }while (subChoice != 3);
                    break;
                }
                case 5:{
                    break;
                }
                default:{
                    System.out.println("Invalid entry, please try again");
                }
            }
        }
    }
}
