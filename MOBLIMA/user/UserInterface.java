package MOBLIMA.user;

import MOBLIMA.ObjectsIO;
import MOBLIMA.admin.*;
import java.util.*;

/**
 * UserInterface class for all the functions for the Moviegoer module
 * such as viewing movie information and booking tickets.
 * @author CE2002 SE3 Group 4
 */

public class UserInterface {
    private static ArrayList<Booking> bookings = (ArrayList<Booking>) MOBLIMA.ObjectsIO.ReadObject(Booking.getFilepath());


    public static ArrayList<Booking> getBookings(String n) {
        ArrayList<Booking> usrBooking = new ArrayList<Booking>();
        for (Booking b : bookings) {
            if (b.getName().equals(n)) {
                usrBooking.add(b);
            }
        }
        return usrBooking;
    }


    protected static void addReview(Movie m){
        Review r = new Review();
        Scanner sc = new Scanner(System.in);
        int rate;
        do {
            try {
                System.out.println("What is your score for the movie " + m.getTitle() + " ?");
                rate = Integer.parseInt(sc.nextLine());
                if (rate < 0 || rate > 5){
                    throw new NumberFormatException();
                }
            }catch (Exception e){
                System.out.println("Input a rate between 0 and 5");
                rate = -1;
            }
        }while (rate < 0);
        r.rating = rate;
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
            try {
                Choice = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException | InputMismatchException e){
                System.out.println("Please input a number.");
                Choice = 20;
            }
            switch (Choice){
                case 0:{
                    break;
                }
                case 1:{
                    MovieListing.showMovies();
                    boolean tryagain;
                    int subChoice = 2;
                    do {
                        try {
                            System.out.println("\nDo you want to display movie details ? Hit 1 for yes, 2 for no");
                            subChoice = Integer.parseInt(sc.nextLine());
                            tryagain = false;
                        }catch (NumberFormatException | InputMismatchException e){
                            System.out.println("Please input a valid entry");
                            tryagain = true;
                        }
                    }while (tryagain);
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
                        while (tryagain && !abort) {
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
                                        if (!(r.matches("^[A-Z]+"))){
                                            throw new InputMismatchException();
                                        }
                                        System.out.println("Input desired Column:");
                                        c = Integer.parseInt(sc.nextLine());
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
                                    do {
                                        try {
                                            Ticket.AgeClasses ticketAge = Ticket.AgeClasses.valueOf(sc.nextLine());
                                            Ticket newTicket = new Ticket(0.f, ticketAge, selectedST, cineplex, cinema, r, c);
                                            newTicket.setPrice(MovieListing.getMovie(movieTitle), cinema);
                                            tickets.add(newTicket);
                                            tryagain = false;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Please input a valid age class.");
                                            tryagain = true;
                                        }
                                    }while (tryagain);
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
        int nbOfTickets = 0;
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        do {
            try {
                System.out.println("How many tickets do you want to book ?");
                nbOfTickets = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                nbOfTickets = 11;
            }
            if (nbOfTickets > 10 || nbOfTickets < 1){
                System.out.println("Please input a number of tickets between 1 and 10.");
            }
        }while(nbOfTickets > 10 || nbOfTickets < 1);
        char choice;
        do {
            try {
                System.out.println("Are theses tickets for the same showtime and movie ? Hit Y for yes or N for no");
                choice = sc.nextLine().charAt(0);
            }catch (Exception e){
                choice = 'e';
            }
            switch(choice) {
                case 'Y': {
                    tickets = selectTickets(nbOfTickets);
                    break;
                }
                case 'N': {
                    for (int i = 0; i < nbOfTickets; i++) {
                        ArrayList<Ticket> t = new ArrayList<Ticket>();
                        t = selectTickets(1);
                        if (t != null && t.size()>0) {
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
                    boolean tryagain;
                    do{
                        try {
                            System.out.println("Hit 0 to go back to main");
                            System.out.println("To select the cinema you want to display future showtimes, please select the cineplex first :");
                            CineplexListing.showCineplexes();
                            subChoice = sc.nextInt() -1;
                            sc.nextLine();
                        }catch (Exception e){
                            subChoice = -2;
                        }
                        if (subChoice == -1){
                            tryagain = false;
                        }
                        else if (subChoice > -1 && subChoice < CineplexListing.getNbOfCineplexes()) {
                            CineplexListing.showShowtimes(subChoice);
                            tryagain = false;
                        }
                        else{
                            System.out.println("Please input valid entry");
                            sc.nextLine();
                            tryagain = true;
                        }
                    }while (tryagain);
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
                        usrBooking = getBookings(subChoice);
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
                                boolean tryagain = true;
                                String cineID = tickets.get(0).getCineCode();
                                String name;
                                String phoneNb;
                                String email;
                                System.out.println();
                                System.out.println("What is your Name ?");
                                name = sc.nextLine();
                                 do{
                                    System.out.println("What is your Phone number ?");
                                    phoneNb = sc.nextLine();
                                    if (phoneNb.matches("^\\d{8,11}(?!\\d)$")){
                                        tryagain = false;
                                    }
                                    else{
                                        System.out.println("Please input a phone number composed of 8 to 11 digits only.");
                                        tryagain = true;
                                    }
                                }while(tryagain);
                                do {
                                    System.out.println("What is your email ?");
                                    email = sc.nextLine().toLowerCase();
                                    if (email.matches("^(.+)@(.+).(.+)$")){
                                        tryagain = false;
                                    }
                                    else{
                                        System.out.println("Please input a valid email : example@something.domain");
                                        tryagain = true;
                                    }
                                }while (tryagain);
                                Booking b = new Booking(name, phoneNb, email, cineID, tickets);
                                bookings.add(b);
                                System.out.println("Recap :");
                                b.showBooking();
                                System.out.println("Please proceed to payment");
                                do {
                                    System.out.println("Key in your CB number:");
                                    String cb = sc.nextLine();
                                    if (cb.matches("^\\d{16}$")){
                                        tryagain = false;
                                    }
                                    else{
                                        System.out.println("Please input a card number composed of 16 digits.");
                                        tryagain = true;
                                    }
                                }while (tryagain);
                                System.out.println("Payment is successful.");
                                ObjectsIO.WriteObject(bookings, Booking.getFilepath());
                                ObjectsIO.WriteObject(MovieListing.getMovies(), MovieListing.getFilepath());
                                ObjectsIO.WriteObject(CineplexListing.getCineplexes(), CineplexListing.getFilepath());
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
                            ObjectsIO.WriteObject(MovieListing.getMovies(), MovieListing.getFilepath());
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
