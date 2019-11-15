package admin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema {

    enum cinemaClasses {
        Gold,
        Normal,
    }

    private cinemaClasses cinemaClass;
    private String cineCode;
    private int numOfSeat;
    private ArrayList<Seat> layout;
    private ArrayList<Showtime> showtimes;



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
    }

    protected void setLayout(int numSeat){
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

    protected void setClass(int cineClass){
        switch (cineClass) {
            case 1:
                cinemaClass = cinemaClasses.Gold;
                break;
            case 2:
                cinemaClass = cinemaClasses.Normal;
                break;
        }
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

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
                    System.out.println("[" + movieST.indexOf(s) + "]  " + m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
                }
            }
            return movieST;
        }
        else{return null;}
    }

    public String getCineCode() {
        return cineCode;
    }
    public void setCineCode(String c) {
        cineCode = c;
    }

    protected void showShowtimes() {
        if (showtimes != null) {
            for (Showtime s : showtimes) {
                LocalDateTime show = s.getMovieShowtime();
                Movie m = s.getMovie();
                if (show.isAfter(LocalDateTime.now()) && (m.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                    System.out.println(m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
                }
            }
        }
        else{
            System.out.println("No showtimes in this cinema yet");
        }
    }

    public int getNumOfSeat() {
        return numOfSeat;
    }

    public cinemaClasses getCinemaClass() {
        return cinemaClass;
    }

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

    public ArrayList<Seat> getLayout() {
        return layout;
    }

    protected void addShowtime(Showtime s) {
    }
}