package MOBLIMA.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cineplex class for cineplex information (e.g. number of cinemas).
 * @author CE2002 SE3 Group 4
 */

public class Cineplex implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int nbOfCinema;
    private ArrayList<Cinema> cinemas;

    /**
     * Creates a cineplex with 3 cinemas included.
     * @param n Name of cineplex
     */
    Cineplex(String n){
        nbOfCinema=0;
        cinemas = new ArrayList<Cinema>();
        name=n;
        threeNewCinema();
    }

    /**
     * Adds a cinema to the cineplex.
     * @param cine Cinema
     */
    void addCinema(Cinema cine) {
        cinemas.add(cine);
        nbOfCinema+=1;
    }

    /**
     * Gets the name of the cineplex.
     * @return Name of cineplex
     */
    public String getName(){return name;}

    /**
     * Sets the name of the cineplex.
     * @param n Name of cineplex
     */
    void setName(String n){name = n;}

    /**
     * Gets the number of cinemas in the cineplex.
     * @return Number of cinemas
     */
    public  int getCinemaNb(){
        return nbOfCinema;
    }

    /**
     * Gets a list of cinemas in the cineplex.
     * @return List of cinemas
     */
    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    /**
     * Gets a list of movies in the cinemas.
     * @return List of movies
     */
    public ArrayList<String> getMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                if (cine.getShowtimes() != null) {
                    for (Showtime s : cine.getShowtimes()) {
                        boolean in = movies.contains(s.getMovie().getTitle());
                        String title = s.getMovie().title;
                        boolean EOS = (MovieListing.getMovie(title).getShowingStatus().equals(Movie.ShowingStatus.End_Of_Showing) || MovieListing.getMovie(title).getShowingStatus().equals(Movie.ShowingStatus.Coming_Soon));
                        if (!in && !EOS) {
                            movies.add(s.getMovie().getTitle());
                        }
                    }
                }
            }
            return movies;
        }
        else {return null;}
    }

    /**
     * Gets the cinema of a cineplex given a cinema code.
     * @param code Cinema code
     * @return Cinema
     */
    Cinema getCinema(String code){
        for (Cinema c : cinemas) {
            if (c.getCineCode().equals(code)){
                return c;
            }
        }
        return null;
    }

    /**
     * Displays the showtimes of the cinemas.
     */
    void showShowtimes(){
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                System.out.println("Cinema " + cine.getCineCode() + ":");
                cine.showShowtimes();
            }
        }
        else{
            System.out.println("No cinema in this cineplex");
        }
    }

    /**
     * Displays the information of a cinema, such as the cinema code, class
     * and number of seats.
     * @param i Index
     */
    public void showCinemaInfo(int i){
        if (cinemas != null) {
            System.out.println("cine code=" + cinemas.get(i).getCineCode());
            System.out.println("cine number of seats=" + cinemas.get(i).getNumOfSeat());
            System.out.println("cine Class=" + cinemas.get(i).getCinemaClass());
        }
        else{
            System.out.println("No cinema found in this cineplex");
        }

    }

    /**
     * Creates 3 new cinemas upon creating a new cineplex.
     */
    private void threeNewCinema(){
        Cinema cine;
        System.out.println("You need to add 3 new cinema when creating a new cineplex");
        for (int i = 0;i<3;i++) {
            cine = new Cinema(chooseNewCinemaCode(),chooseNewCinemaClass());
            this.addCinema(cine);
        }
    }

    /**
     * For inputting a cinema code for the newly created cinema.
     * @return Cinema code
     */
    static String chooseNewCinemaCode(){
        String newCineCode;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("what is the cinema code enter exactly 3 character ");
            newCineCode = input.next();
        } while (newCineCode.length() != 3);
        return newCineCode;
    }

    /**
     * For selecting a cinema class for the newly created cinema.
     * @return Cinema class
     */
    static int chooseNewCinemaClass() {
     int newCineClass;
     Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("what is the cinema class 1 for Gold, 2 for Normal");
                newCineClass = Integer.parseInt(input.nextLine());
            }catch (Exception e){
                newCineClass = 0;
                System.out.println("Please input a valid entry");
            }
        } while (newCineClass < 1 || newCineClass > 2);
        return newCineClass;
    }

    /**
     * Adds a showtime for a cinema.
     * @param cineCode Cinema code
     * @param s Showtime
     */
    void addShowtime(String cineCode, Showtime s){
        for (Cinema c : cinemas){
            if (c.getCineCode().equals(cineCode)){
                c.addShowtime(s);
                System.out.println("Added successfully");
            }
        }
    }

    /**
     * Removes a cinema from the cineplex.
     * @param c Cinema
     */
    void removeCine(Cinema c){
        cinemas.remove(c);
    }
}


