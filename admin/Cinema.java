package admin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema {


    Cinema() {
        numOfSeat = 1;
        availableSeat = 1;
        cineCode = "aaa";
        cinemaClass = cinemaClassEnum.Normal;
    }

    private int numOfSeat;
    private int availableSeat;
    private ArrayList<Seat> layout;
    private ArrayList<Showtime> showtimes;


    Cinema(int seats, String code, int cineClass) {
        numOfSeat = seats;
        availableSeat = numOfSeat;
        cineCode = code;
        switch (cineClass) {
            case 1:
                cinemaClass = cinemaClassEnum.Gold;
                break;
            case 2:
                cinemaClass = cinemaClassEnum.Normal;
                break;
        }
    }

    private enum cinemaClassEnum {
        Gold,
        Normal,
    }

    private cinemaClassEnum cinemaClass;
    private String cineCode;

    // private Showtimes : Showtime[];
    //layout: Seat[];
    protected void addShowtime(Showtime s) {
    }

    ;

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    ;

    public ArrayList<Seat> getLayout() {
        return layout;
    }

    ;

    public void bookSeat(Seat s) {
        s.book();
        availableSeat -= 1;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    //public getShowTimes();
    // package admin addShowTimes ()
    public String getCineCode() {
        return cineCode;

    }

    public int getNumOfSeat() {
        return numOfSeat;
    }

    public cinemaClassEnum getCinemaClass() {
        return cinemaClass;
    }
    //public getLayout(){}
    //public bookSeats ()
    //public getAvailable(){}
    //public getClass

    protected void showShowtimes() {
        for (Showtime s : showtimes) {
            LocalDateTime show = s.getMovieShowtime();
            Movie m = s.getMovie();
            if (show.isAfter(LocalDateTime.now()) && (m.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                System.out.println(m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
            }
        }
    }
}