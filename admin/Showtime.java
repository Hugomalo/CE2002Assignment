package admin;

import java.time.LocalDateTime;

public class Showtime {
    private Movie movie;
    private LocalDateTime movieShowtime;

    protected Movie getMovie(){return movie;}
    protected LocalDateTime getMovieShowtime(){return movieShowtime;}
}