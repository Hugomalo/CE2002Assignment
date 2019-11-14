package admin;

import user.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showtime {
    private Movie movie;
    private LocalDateTime movieShowtime;
    private ArrayList<Seat> availableSeats;

    public Movie getMovie(){return movie;}
    public LocalDateTime getMovieShowtime(){return movieShowtime;}

    public ArrayList<Seat> getAvailableSeats() {
        return availableSeats;
    }
    public boolean book(Seat.Row Row, int column){
        int i = 0;
        while((i < availableSeats.size()) && (availableSeats.get(i).getRow().equals(Row) && (availableSeats.get(i).getColumn() == column))){
            i++;
        }
        if (i<availableSeats.size()){
            availableSeats.remove(i);
            return true;
        }
        return false;
    }
}