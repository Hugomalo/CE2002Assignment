package MOBLIMA.admin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Showtime implements Serializable {
    private static final long serialVersionUID = 1L;

    private Movie movie;
    private LocalDateTime movieShowtime;
    private ArrayList<Seat> availableSeats;

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

    public Movie getMovie(){return movie;}
    public LocalDateTime getMovieShowtime(){return movieShowtime;}


    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }
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