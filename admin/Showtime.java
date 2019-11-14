package admin;

import user.Ticket;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Showtime {
    private Movie movie;
    private LocalDateTime movieShowtime;
    private ArrayList<Seat> availableSeats;

    protected Showtime(Movie m, int month, int day, int hour, int minute){
        int year;
        LocalDateTime curr = LocalDateTime.now();
        if (month < curr.getMonthValue()){
            year = curr.getYear() + 1;
        }
        else{
            year = curr.getYear();
        }
        movieShowtime = LocalDateTime.of(year, Month.of(month), day, hour, minute, 0);
        movie = m;
    }

    public Movie getMovie(){return movie;}
    public LocalDateTime getMovieShowtime(){return movieShowtime;}


    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }
    public boolean book(String Row, int column){
        Seat.Row row = Seat.Row.valueOf(Row);
        int i = 0;
        while((i < availableSeats.size()) && (availableSeats.get(i).getRow().equals(row) && (availableSeats.get(i).getColumn() == column))){
            i++;
        }
        if (i<availableSeats.size()){
            availableSeats.remove(i);
            return true;
        }
        return false;
    }
}