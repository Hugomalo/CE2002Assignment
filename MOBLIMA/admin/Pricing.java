package MOBLIMA.admin;

import MOBLIMA.user.Calendar;
import MOBLIMA.user.Ticket;
import MOBLIMA.admin.Movie.MovieTypes;
import MOBLIMA.admin.Movie.ShowingStatus;

/**
 * Pricing class for all pricing information such as discounts and surcharges.
 * @author CE2002 SE3 Group 4
 */

public class Pricing {
    private static Float basePrice = 5.f;
    private static Float stdDiscount = 1.f;
    private static Float elderDiscount = 2.f;
    private static Float sunPHSurcharge = 1.f;
    private static Float _3DSurcharge = 0.5f;
    private static Float BBSurcharge = 0.5f;
    private static Float prwSurcharge = 1.5f;
    private static Float classSurcharge = 2.f;

    /**
     * Calculates the price of a ticket.
     * @param t Ticket
     * @param a Age class converted to string
     * @param m Movie
     * @param c Cinema
     * @return Ticket price
     */
    public static Float priceCalc(Ticket t, String a, Movie m, Cinema c){
    	float prcOfTicket = basePrice;
    	
    	if (a.equals("student")) {
    		prcOfTicket -= stdDiscount;
    	}
    	else if (a.equals("senior_citizen")) {
    		prcOfTicket -= elderDiscount;
    	}
    	
    	if (m.getType().equals(MovieTypes.valueOf("_3D"))) {
			prcOfTicket += _3DSurcharge;
    	}
    	else if (m.getType().equals(MovieTypes.valueOf("Blockbuster"))) {
    		prcOfTicket += BBSurcharge;
		}
    	
    	if (m.getShowingStatus().equals(ShowingStatus.valueOf("Preview"))) {
    		prcOfTicket += prwSurcharge;
    	}
    	
    	if (Calendar.isPH(t.getShowtime().getMovieShowtime())) {
    		prcOfTicket += sunPHSurcharge;
    	}
    	
    	if (c.getCinemaClass().equals(Cinema.cinemaClasses.valueOf("Gold"))) {
    		prcOfTicket += classSurcharge;
    	}
    	
    	return prcOfTicket;
    }
    
    /**
     * Sets the base price of a ticket.
     * @param p Base price
     */
    protected static void setBasePrice(Float p){
        basePrice = p;
    }
    
    /**
     * Sets all the discounts and surcharges that can be included in a ticket.
     * @param d1 Student discount
     * @param d2 Senior citizen discount
     * @param s1 Surcharge for Sundays/Public Holidays
     * @param s2 3D movie surcharge
     * @param s3 Blockbuster movie surcharge
     * @param s4 Preview movie surcharge
     * @param s5 Gold class cinema surcharge
     */
    protected static void setDiscountSurcharge(Float d1, Float d2, Float s1, Float s2, Float s3, Float s4, Float s5){
    	stdDiscount = d1;
    	elderDiscount = d2;
    	sunPHSurcharge = s1;
    	_3DSurcharge = s2;
    	BBSurcharge = s3;
    	prwSurcharge = s4;
    	classSurcharge = s5;
    }
}
