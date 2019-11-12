package user;

import admin.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        sc.nextLine();
        System.out.println("Please tell us more, what did you think of " + m.getTitle() + " ?");
        r.review = sc.nextLine();
        m.addReview(r);
    }



    public static ArrayList<Ticket> selectTickets(int nbOfTicket){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Scanner sc = new Scanner(System.in);
        int cpIndex;
        Cineplex cineplex;
        do {
            System.out.println("To select the cinema you want to display showtimes, please select the cineplex first :");
            CineplexListing.showCineplexes();
            cpIndex = sc.nextInt();
            sc.nextLine();
            if (cpIndex > 0 && cpIndex < CineplexListing.getNbOfCineplexes()) {
                cineplex = CineplexListing.getCineplex(cpIndex-1);
                String movieTitle;
                System.out.println("Movies available at " + cineplex.getName() + "cpIndex");
                ArrayList<String> movies = new ArrayList<>(cineplex.getMovies());
                for (String movie : movies){
                    System.out.println(movie);
                }
                do {
                    System.out.println("What movie do you want to watch ? : (please input Title");
                    movieTitle = sc.nextLine();
                    if (!movies.contains(movieTitle)){
                        System.out.println("Please input a valid movie Title");
                    }
                }while (!movies.contains(movieTitle));
                System.out.println("Here are the available showtimes for each cinema for" + movieTitle);
                ArrayList<Cinema> cinemas = new ArrayList<Cinema>(cineplex.getCinemas());
                Map<Integer, ArrayList<Showtime>> movieST = new HashMap<>();
                for (Cinema cine : cinemas){
                    System.out.println("[" + cinemas.indexOf(cine) + "]" + cine.getCineCode());
                    movieST.put(cinemas.indexOf(cine), cine.getShowShowtimes(movieTitle));
                }
                int cineIndex;
                Cinema cinema;
                Showtime selectedST;
                System.out.println("Please input desired cinema index :");
                cineIndex = sc.nextInt();
                cinema = cinemas.get(cineIndex);
                sc.nextLine();
                System.out.println("Please input the desired showtime at cinema " + cinema.getCineCode());
                selectedST = movieST.get(cineIndex).get(sc.nextInt());
                for (int i=0; i<nbOfTicket; i++){
                    System.out.println("What is the age class for the ticket nb " + i + "among :");
                    for (Ticket.AgeClasses c : Ticket.AgeClasses.values()){
                        System.out.println(c);
                    }
                    Ticket.AgeClasses ticketAge = Ticket.AgeClasses.valueOf(sc.nextLine());
                    Ticket newTicket = new Ticket((float) 0, ticketAge, selectedST, cineplex, cinema);
                    newTicket.setPrice(MovieListing.getMovie(movieTitle), cinema);
                    tickets.add(newTicket);
                }
            }
            else {
                System.out.println("Please input valid entry");
            }
        }while (cpIndex > 0 && cpIndex < CineplexListing.getNbOfCineplexes());
        return tickets;
    }

    public static ArrayList<Ticket> book(){
        Scanner sc = new Scanner(System.in);
        int nbOfTickets;
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        do {
            System.out.println("How many tickets do you want to book ?");
            nbOfTickets = sc.nextInt();
            if (nbOfTickets > 10 || nbOfTickets < 1){
                System.out.println("Please input a number of tickets between 1 and 10.");
            }
        }while(nbOfTickets > 10 || nbOfTickets < 1);
        char choice;
        do {
            System.out.println("Are theses tickets for the same showtime and movie ? Hit Y for yes or N for no");
            choice = sc.nextLine().charAt(0);
            switch(choice) {
                case 'Y': {
                    tickets = selectTickets(nbOfTickets);
                    break;
                }
                case 'N': {
                    for (int i = 0; i < nbOfTickets; i++) {
                        ArrayList<Ticket> t = selectTickets(1);
                        tickets.add(t.get(0));
                    }
                    break;
                }
                default: {
                    System.out.println("Please input a valid entry");
                    break;
                }
            }
        }while(choice != 'Y' || choice != 'N');
        return tickets;
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
                        break;
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
                            ArrayList<Ticket> tickets = new ArrayList<Ticket>(book());
                            String cineID = tickets.get(0).getCineCode();
                            String name;
                            int phoneNb;
                            String email;
                            System.out.println();
                            System.out.println("What is your Name ?");
                            name = sc.nextLine();
                            System.out.println("What is your Phone number ?");
                            phoneNb = Integer.parseInt(sc.nextLine(), 10);
                            System.out.println("What is your email ?");
                            email = sc.nextLine().toLowerCase();
                            bookings.add(new Booking(name, phoneNb, email, cineID, tickets));
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
