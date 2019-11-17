package MOBLIMA.admin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Cinema class for cinema information in a cineplex.
 * @author CE2002 SE3 Group 4
 */

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;

    enum cinemaClasses {
        Gold,
        Normal,
    }

    private cinemaClasses cinemaClass;
    private String cineCode;
    private int numOfSeat;
    private ArrayList<Seat> layout;
    private ArrayList<Showtime> showtimes;


    /**
     * Creates a cinema with a seat layout and an array of
     * showtimes, given the cinema code and class.
     * @param code Cinema code
     * @param cineClass Cinema class (1 for Gold, 2 for Normal)
     */
    Cinema(String code, int cineClass) {
        Seat s;
        numOfSeat = 100;
        cineCode = code;
        switch (cineClass) {
            case 1:
                cinemaClass = cinemaClasses.Gold;
                break;
            case 2:
                cinemaClass = cinemaClasses.Normal;
                break;
        }
        layout=new ArrayList<>();
        for (Seat.Row r : Seat.Row.values())
        {
            for(int j =1;j<=10;j++)
            {
                s=new Seat(r,j);
                layout.add(s);
            }
        }
        showtimes = new ArrayList<Showtime>();
    }

    /**
     * Sets the layout of the cinema.
     * @param numSeat Number of seats
     */
    void setLayout(int numSeat){
        Seat s;
        layout=new ArrayList<>();
        numOfSeat = numSeat;
        for (Seat.Row r : Seat.Row.values())
        {
            for(int j =1;j<=Math.sqrt(numSeat);j++)
            {
                s=new Seat(r,j);
                layout.add(s);
            }
        }
    }

    /**
     * Sets the class (Gold/Normal) of the cinema.
     * @param cineClass Cinema class
     */
    void setClass(int cineClass){
        switch (cineClass) {
            case 1:
                cinemaClass = cinemaClasses.Gold;
                break;
            case 2:
                cinemaClass = cinemaClasses.Normal;
                break;
        }
    }

    /**
     * Gets the showtimes of a particular cinema.
     * @return Showtimes of the cinema
     */
    ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    /**
     * Gets and show the showtimes of a movie in a cinema.
     * @param title Title of movie
     * @return Showtimes of movie in a cinema
     */
    public ArrayList<Showtime> getShowShowtimes(String title){
        ArrayList<Showtime> movieST = new ArrayList<Showtime>();
        if (showtimes != null) {
            for (Showtime st : showtimes) {
                if (st.getMovie().getTitle().equals(title) && st.getMovieShowtime().isAfter(LocalDateTime.now())) {
                    movieST.add(st);
                }
            }
            for (Showtime s : movieST) {
                LocalDateTime show = s.getMovieShowtime();
                Movie m = s.getMovie();
                if (show.isAfter(LocalDateTime.now()) && (m.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                    System.out.println("    [" + (movieST.indexOf(s)+1) + "]  " + m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
                }
            }
            return movieST;
        }
        else{return null;}
    }

    /**
     * Gets the cinema code of a cinema.
     * @return Cinema code
     */
    public String getCineCode() {
        return cineCode;
    }

    /**
     * Sets the cinema code of a cinema.
     * @param c Cinema code
     */
    void setCineCode(String c) {
        cineCode = c;
    }

    /**
     * Retrieves the showtimes of a movie that is under
     * 'Coming Soon', 'Preview' or 'Now Showing' in a cinema.
     */
    void showShowtimes() {
        if (showtimes != null) {
            for (Showtime s : showtimes) {
                LocalDateTime show = s.getMovieShowtime();
                Movie m = MovieListing.getMovie(s.getMovie().title);
                if (show.isAfter(LocalDateTime.now()) && (m.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                    System.out.println("    " + m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
                }
            }
        }
        else{
            System.out.println("No showtimes in this cinema yet");
        }
    }

    /**
     * Removes a showtime of a cinema.
     * @param s Cinema showtime
     */
    void removeShowtime(Showtime s){
        showtimes.remove(s);
    }

    /**
     * Gets the number of seats in a cinema.
     * @return Number of seats
     */
    int getNumOfSeat() {
        return numOfSeat;
    }

    /**
     * Gets the class of a cinema.
     * @return Cinema class
     */
    cinemaClasses getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Displays the layout of a cinema.
     */
    public void showLayout(){
        int i=0;
        System.out.println("    1  2  3    4  5  6  7  8  9  10 \n");
        for (Seat.Row r : Seat.Row.values())
        {
            System.out.print(r+"  ");
            for(int j =1;j<=10;j++)
            {
                System.out.print("[");
                System.out.print(" ");
                System.out.print("]");
                if (j==3){
                    System.out.print("  ");
                }
                i++;
            }
            System.out.println();
        }
    }

    /**
     * Gets the layout of the cinema.
     * @return Layout of cinema
     */
    ArrayList<Seat> getLayout() {
        return layout;
    }

    /**
     * Adds a showtime for a cinema.
     * @param s Showtime
     */
    void addShowtime(Showtime s) {
        showtimes.add(s);
    }
}