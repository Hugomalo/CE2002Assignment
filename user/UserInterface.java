package user;

import admin.*;
import com.sun.source.tree.WhileLoopTree;

import javax.management.loading.PrivateMLet;
import java.util.*;

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

    protected static void addReview(Movie m){
        Review r = new Review();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your score for the movie " + m.getTitle() + " ?");
        r.rating = sc.nextInt();
        sc.nextLine();
        System.out.println("Please tell us more, what did you think of " + m.getTitle() + " ?");
        r.review = sc.nextLine();
        m.addReview(r);
    }

    public static void showAllMovies(){
        Scanner sc = new Scanner(System.in);
        int Choice;
        do {
            System.out.println("Do you want to see all movies (including not showing) ? Hit 1");
            System.out.println("Do you want to see top 5 movies ranking by ticket sales ? Hit 2");
            System.out.println("Do you want to see top 5 movies ranking by overall reviews ? Hit 3");
            System.out.println("Hit 0 to go back to main");
            Choice = sc.nextInt();
            sc.nextLine(); // to avoid skipping of next sc instruction
            switch (Choice){
                case 0:{
                    break;
                }
                case 1:{
                    MovieListing.showMovies();
                    int subChoice;
                    System.out.println("Do you want to display movie details ? Hit 1 for yes, 2 for no");
                    subChoice = sc.nextInt();
                    boolean movieHit = false;
                    if (subChoice == 1){
                        while (!movieHit) {
                            String title;
                            System.out.println("What is the title of the Movie you want to get details ?");
                            title = sc.nextLine();
                            movieHit = MovieListing.getMovieDetails(title);
                            if (!movieHit) {
                                System.out.println("Please input a valid movie title.");
                            }
                        }
                    }
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
        }while (Choice != 0);


    }

    private static ArrayList<Ticket> selectTickets(int nbOfTicket){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Scanner sc = new Scanner(System.in);
        int cpIndex = -1;
        Cineplex cineplex;
        String movieTitle = "";
        boolean abort = false;
        do {
            System.out.println("To select the cinema you want to display showtimes, please select the cineplex first : (Hit 0 to abort)");
            CineplexListing.showCineplexes();
            try {
                cpIndex = sc.nextInt();
                sc.nextLine();
                if (cpIndex == 0) {
                    abort = true;
                } else if (cpIndex > 0 && cpIndex <= CineplexListing.getNbOfCineplexes()) {
                    cineplex = CineplexListing.getCineplex(cpIndex - 1);
                    System.out.println("Movies available at " + cineplex.getName());
                    ArrayList<String> movies = new ArrayList<>(cineplex.getMovies());
                    for (String movie : movies) {
                        System.out.println(movie);
                    }
                    do {
                        System.out.println("What movie do you want to watch ? : (please input Title)");
                        movieTitle = sc.nextLine();
                        if (movieTitle.equals("0")) {
                            abort = true;
                        } else if (!movies.contains(movieTitle)) {
                            System.out.println("Please input a valid movie Title. Hit 0 to abort");
                        }
                    } while (!movies.contains(movieTitle) && !abort);
                    if (!abort) {
                        System.out.println("Here are the available showtimes for each cinema for" + movieTitle);
                        ArrayList<Cinema> cinemas = new ArrayList<Cinema>(cineplex.getCinemas());
                        Map<Integer, ArrayList<Showtime>> movieST = new HashMap<>();
                        for (Cinema cine : cinemas) {
                            System.out.println("[" + (cinemas.indexOf(cine)+1) + "]" + cine.getCineCode());
                            movieST.put(cinemas.indexOf(cine), cine.getShowShowtimes(movieTitle));
                        }
                        int cineIndex = -1;
                        Cinema cinema = null;
                        Showtime selectedST = null;
                        boolean tryagain = true;
                        while (tryagain) {
                            try {
                                System.out.println("Please input desired cinema index :");
                                cineIndex = sc.nextInt() -1;
                                if (cineIndex == -1){
                                    abort = true;
                                    tryagain = false;
                                }
                                else {
                                    cinema = cinemas.get(cineIndex);
                                    sc.nextLine();
                                    tryagain = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Please input a valid cinema, Hit 0 to abort");
                                tryagain = true;
                            }
                        }
                        tryagain = true;
                        while (tryagain) {
                            try {
                                System.out.println("Please input the desired showtime at cinema " + cinema.getCineCode());
                                int a = sc.nextInt() -1;
                                sc.nextLine();
                                if (a == -1){
                                    abort = true;
                                    tryagain = false;
                                }
                                else {
                                    selectedST = movieST.get(cineIndex).get(a);
                                    tryagain = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Please input a valid index for showtime. Hit 0 to abort");
                                tryagain = true;
                            }
                        }
                        if (!abort) {
                            for (int i = 0; i < nbOfTicket; i++) {
                                String r = "Z";
                                int c = -1;
                                ArrayList<Seat> avlbSeat = new ArrayList<>();
                                avlbSeat = selectedST.getAvailableSeats();
                                System.out.println("Here is the layout of the room at cinema " + cinema.getCineCode() + " :");
                                cinema.showLayout();
                                System.out.println("Please select a seat from the list below:");
                                for (Seat s : avlbSeat) {
                                    System.out.println("Row: " + s.getRow() + " ; " + "Column: " + s.getColumn());
                                }
                                do {
                                    try {
                                        System.out.println("Input desired Row:");
                                        r = sc.nextLine();
                                        System.out.println("Input desired Column:");
                                        c = sc.nextInt();
                                        sc.nextLine();
                                        tryagain = false;
                                    } catch (Exception e) {
                                        System.out.println("Please input a valid entry for seat selection");
                                        tryagain = true;
                                    }
                                } while (tryagain);
                                if (selectedST.book(r, c)) {
                                    System.out.println("What is the age class for the ticket nb " + (i+1) + " among :");
                                    for (Ticket.AgeClasses ac : Ticket.AgeClasses.values()) {
                                        System.out.println(ac);
                                    }
                                    Ticket.AgeClasses ticketAge = Ticket.AgeClasses.valueOf(sc.nextLine());
                                    Ticket newTicket = new Ticket((float) 0, ticketAge, selectedST, cineplex, cinema, r, c);
                                    newTicket.setPrice(MovieListing.getMovie(movieTitle), cinema);
                                    tickets.add(newTicket);
                                } else {
                                    System.out.println("This seat is not among list of available seats.");
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Please input valid entry");
                    abort = false;
                }
            }catch (InputMismatchException e){
                System.out.println("Please input a valid entry type.");
                sc.nextLine();
                abort = false;
            }
        }while (!(cpIndex > 0 && cpIndex <= CineplexListing.getNbOfCineplexes()) && !abort);
        if(abort){
            return null;
        }
        else{
            Objects.requireNonNull(MovieListing.getMovie(movieTitle)).addSales(nbOfTicket);
            return tickets;
        }
    }

    private static ArrayList<Ticket> book(){
        Scanner sc = new Scanner(System.in);
        int nbOfTickets;
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        do {
            System.out.println("How many tickets do you want to book ?");
            nbOfTickets = sc.nextInt();
            sc.nextLine();
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
                        ArrayList<Ticket> t = new ArrayList<Ticket>();
                        t = selectTickets(1);
                        if (t != null) {
                            tickets.add(t.get(0));
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Please input a valid entry");
                    break;
                }
            }
        }while(choice != 'Y' && choice != 'N');
        return tickets;
    }

    public static void mainSwitch(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            System.out.println("What action do you want to do ?");
            System.out.println("Hit 1 to display movie listing, hit 2 to display showtimes, hit 3 to view your bookings, hit 4 to book tickets, Hit 5 to write a review, 6 to quit");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:{
                    showAllMovies();
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
                        ArrayList<Booking> usrBooking = new ArrayList<Booking>();
                        for (Booking b : bookings){
                            if (b.getName().equals(subChoice)){
                                usrBooking.add(b);
                            }
                        }
                        if(usrBooking.size() == 0){
                            System.out.println("No booking found under name " + subChoice + ".");
                        }
                        else{
                            System.out.println(usrBooking.size() + " bookings found with name " + subChoice + ":");
                            for (Booking b : usrBooking){
                                b.showBooking();
                            }
                        }
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
                            try {
                                ArrayList<Ticket> tickets = new ArrayList<Ticket>(book());
                            if(tickets.size() > 0) {
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
                                Booking b = new Booking(name, phoneNb, email, cineID, tickets);
                                bookings.add(b);
                                System.out.println("Recap :");
                                b.showBooking();
                                System.out.println("Please proceed to payment");
                                System.out.println("Key in your CB number:");
                                sc.nextLine();
                                System.out.println("Payment is successful.");
                            }
                            }catch (NullPointerException e){
                                System.out.println("Abortion of booking");
                            }
                            break;
                        }
                        else{
                            System.out.println("Please input a valid entry");
                        }
                    }while(true);
                    break;
                }
                case 5:{
                    String title;
                    System.out.println("Here are the movies available:");
                    MovieListing.showMovies();
                    System.out.println();
                    Movie m = null;
                    while (m == null){
                        System.out.println("Please input the title of the movie you want to write a review for:");
                        title = sc.nextLine();
                        m = MovieListing.getMovie(title);
                        if (m != null) {
                            addReview(m);
                        } else {
                            System.out.println("Please input a valid Movie name");
                        }
                    }
                    break;
                }
                case 6:{
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
