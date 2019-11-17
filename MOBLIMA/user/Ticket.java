package MOBLIMA.user;

import MOBLIMA.admin.*;
import java.io.Serializable;

/**
 * Ticket class for ticket information.
 * @author CE2002 SE3 Group 4
 */

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

    /**
     * All information of a ticket
     * @param price price
     * @param age age class
     * @param selectedST selected showtime
     * @param cineplex cineplex
     * @param cine cinema code
     * @param row row
     * @param column column
     */
    protected Ticket(Float price, AgeClasses age, Showtime selectedST, Cineplex cineplex, Cinema cine, String row, int column) {
        this.price = price;
        ageClass = age;
        selection = selectedST;
        cineplexName = cineplex.getName();
        cineCode = cine.getCineCode();
        SeatRow = row;
        SeatColumn = column;
    }

    /**
     * Sets the price of a ticket.
     * @param m Movie
     * @param cine Cinema
     */
    protected void setPrice(Movie m, Cinema cine){
        price = Pricing.priceCalc(this, this.ageClass.toString(), m, cine);
    }

    /**
     * Gets the cinema code
     * @return Cinema code
     */
    protected String getCineCode(){return cineCode;}
    /**
     * Gets the showtime
     * @return selected showtime
     */
    public Showtime getShowtime(){return selection;}
    /**
     * gets the ticket price
     * @return price
     */
    protected Float getPrice(){return price;}

    /**
     * Displays the information of each ticket.
     */
    public void showTicket(){
        System.out.println("    Ticket age class: " + ageClass);
        System.out.println("    Cineplex: " + cineplexName +  " Cinema: " + cineCode);
        System.out.println("    Movie: " + selection.getMovie().getTitle() + " on " + selection.getMovieShowtime().getDayOfMonth() + "/" + selection.getMovieShowtime().getMonth() + "/" + selection.getMovieShowtime().getYear() + " at " + selection.getMovieShowtime().getHour() + ":" + selection.getMovieShowtime().getMinute());
        System.out.println("    Seat: " + SeatRow + SeatColumn);
        System.out.println("    Price: " + price + "S$");
        System.out.println();
    }
}
