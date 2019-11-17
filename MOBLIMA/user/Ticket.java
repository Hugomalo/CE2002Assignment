package MOBLIMA.user;

import MOBLIMA.admin.*;

import java.io.Serializable;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    enum AgeClasses{
        adult,
        senior_citizen,
        student
    }

    private Float price;
    private AgeClasses ageClass;
    private Showtime selection;
    private String cineplexName;
    private String cineCode;
    private String SeatRow;
    private int SeatColumn;

    Ticket(Float price, AgeClasses age, Showtime selectedST, Cineplex cineplex, Cinema cine, String row, int column) {
        this.price = price;
        ageClass = age;
        selection = selectedST;
        cineplexName = cineplex.getName();
        cineCode = cine.getCineCode();
        SeatRow = row;
        SeatColumn = column;
    }

    void setPrice(Movie m, Cinema cine){
        price = Pricing.priceCalc(this, this.ageClass.toString(), m, cine);
    }

    String getCineCode(){return cineCode;}
    public Showtime getShowtime(){return selection;}
    Float getPrice(){return price;}

    void showTicket(){
        System.out.println("    Ticket age class: " + ageClass);
        System.out.println("    Cineplex: " + cineplexName +  " Cinema: " + cineCode);
        System.out.println("    Movie: " + selection.getMovie().getTitle() + " on " + selection.getMovieShowtime().getDayOfMonth() + "/" + selection.getMovieShowtime().getMonth() + "/" + selection.getMovieShowtime().getYear() + " at " + selection.getMovieShowtime().getHour() + ":" + selection.getMovieShowtime().getMinute());
        System.out.println("    Seat: " + SeatRow + SeatColumn);
        System.out.println("    Price: " + price + "S$");
        System.out.println();
    }
}
