package user;

import admin.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ticket {

    protected Ticket(Float price, AgeClasses age, Showtime selectedST, Cineplex cineplex, Cinema cine) {
        this.price = price;
        ageClass = age;
        selection = selectedST;
        cineplexName = cineplex.getName();
        cineCode = cine.getCineCode();
    }

    enum AgeClasses{
        adult,
        senior_citizen,
        child,
        student
    }

    private Float price;
    private AgeClasses ageClass;
    private Showtime selection;
    private String cineplexName;
    private String cineCode;
    protected void setPrice(Movie m, Cinema cine){
        price = Pricing.priceCalc(this, m, cine);
    }

    public static ArrayList<Ticket> selectTickets(int nbOfticket){
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
                for (int i=0; i<nbOfticket; i++){
                    System.out.println("What is the age class for the ticket nb " + i + "among :");
                    for (AgeClasses c : AgeClasses.values()){
                        System.out.println(c);
                    }
                    AgeClasses ticketAge = AgeClasses.valueOf(sc.nextLine());
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

    public static ArrayList<Ticket> book(int nbOfticket){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        char choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Are theses tickets for the same showtime and movie ? Hit Y for yes or N for no");
            choice = sc.nextLine().charAt(0);
            switch(choice) {
                case 'Y': {
                    tickets = selectTickets(nbOfticket);
                    break;
                }
                case 'N': {
                    for (int i = 0; i < nbOfticket; i++) {
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

    public Showtime getSelection() {
        return selection;
    }
}
