package admin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cinema {

    private enum cinemaClasses {
        Gold,
        Normal,
    }

    private cinemaClasses cinemaClass;
    private String cineCode;
    private int numOfSeat;
    private int availableSeat;
    private ArrayList<Seat> layout;
    private ArrayList<Showtime> showtimes;

    Cinema() {
        numOfSeat = 1;
        availableSeat = 1;
        cineCode = "aaa";
        cinemaClass = cinemaClasses.Normal;
    }

    Cinema(String code, int cineClass) {
        Seat s;
        numOfSeat = 100;
        availableSeat = numOfSeat;
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

    protected void showShowtimes() {
        for (Showtime s : showtimes) {
            LocalDateTime show = s.getMovieShowtime();
            Movie m = s.getMovie();
            if (show.isAfter(LocalDateTime.now()) && (m.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                System.out.println(m.getTitle() + " : " + show.getMonth() + "/" + show.getDayOfMonth() + " " + show.getHour() + ":" + show.getMinute());
            }
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
        System.out.println("    1  2  3  4  5  6  7  8  9  10 \n");
        for (Seat.Row r : Seat.Row.values())
        {
            System.out.print(r+"  ");
            for(int j =1;j<=10;j++)
            {
                System.out.print("|");
                if (layout.get(i).getAvailable())
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print("X");
                }
                System.out.print("|");
                i++;
            }
            System.out.println();
        }
    }

    public ArrayList<Seat> getLayout() {
        return layout;
    }

    public void bookSeat(Seat s) {
        s.book();
        availableSeat -= 1;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    protected void addShowtime(Showtime s) {
    }
}