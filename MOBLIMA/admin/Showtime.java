package MOBLIMA.admin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Showtime class for showtime information.
 * @author CE2002 SE3 Group 4
 */

public class Showtime implements Serializable {
    private static final long serialVersionUID = 1L;

    private Movie movie;
    private LocalDateTime movieShowtime;
    private ArrayList<Seat> availableSeats;

    /**
     * Creates a showtime for a movie in a cinema.
     * @param m Movie
     * @param month Month
     * @param day Day
     * @param hour Hour
     * @param minute Minute
     * @param c Cinema
     */
    Showtime(Movie m, int month, int day, int hour, int minute, Cinema c){
        int year;
        LocalDateTime curr = LocalDateTime.now();
        if (month < curr.getMonthValue()){
            year = curr.getYear() + 1;
        }
        else{
            year = curr.getYear();
        }
        movieShowtime = LocalDateTime.of(year, Month.of(month), day, hour, minute, 0);
        movie = MovieListing.getMovie(m.title);
        availableSeats = c.getLayout();
    }

    /**
     * Gets the movie from the listing.
     * @return Movie
     */
    public Movie getMovie(){return movie;}

    /**
     * Gets the showtime for a movie.
     * @return Movie showtime
     */
    public LocalDateTime getMovieShowtime(){return movieShowtime;}

    /**
     * Gets the available seats in a cinema.
     * @return List of available seats
     */
    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Checks whether a seat is available for booking.
     * @param Row Row
     * @param column Column
     * @return Boolean result
     */
    public boolean book(String Row, int column){
        Seat.Row row;
        try {
            row = Seat.Row.valueOf(Row);
        }catch (Exception e){
            return false;
        }
        int i = 0;
        while((i < availableSeats.size()) && !((availableSeats.get(i).getRow().equals(row) && (availableSeats.get(i).getColumn() == column)))){
            i++;
        }
        if (i<availableSeats.size()){
            availableSeats.remove(i);
            return true;
        }
        return false;
    }
}