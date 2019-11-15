package admin;

import user.Calendar;
import user.Ticket;

import java.time.LocalDateTime;

import admin.Movie.MovieTypes;
import admin.Movie.ShowingStatus;

public class Pricing {
    private static Float basePrice;
    private static Float stdDiscount;
    private static Float elderDiscount;
    private static Float sunPHSurcharge;
    private static Float _3DSurcharge;
    private static Float BBSurcharge;
    private static Float prwSurcharge;
    private static Float classSurcharge;

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
    protected static void setBasePrice(Float p){
        basePrice = p;
    }
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
