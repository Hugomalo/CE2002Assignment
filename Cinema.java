import java.util.ArrayList;

public class Cinema {
    enum CinemaClasses{
        Platinium_Suite,
        iMax
    };

    private CinemaClasses cinemaClass;
    private int numOfSeat;
    private int availableSeat;
    private ArrayList<Seat> layout;
    private char[] cineCode = new char[3];
    private ArrayList<Showtime> showtimes;


    public char[] getCineCode() {
        return cineCode;
    }

    protected void setCineCode(char[] cineCode) {
        this.cineCode = cineCode;
    }
    protected void addShowtime(Showtime s){};
    public ArrayList<Showtime> getShowtimes() {return showtimes;};
    public ArrayList<Seat> getLayout() {return layout;};
    public void bookSeat(Seat s){
        s.book();
        availableSeat -= 1;
    }
    public int getAvailableSeat() {return availableSeat;}

    public CinemaClasses getCinemaClass() {return cinemaClass;}
}
