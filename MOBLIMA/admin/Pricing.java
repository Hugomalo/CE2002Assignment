package MOBLIMA.admin;

import MOBLIMA.user.Calendar;
import MOBLIMA.user.Ticket;
import MOBLIMA.admin.Movie.MovieTypes;
import MOBLIMA.admin.Movie.ShowingStatus;

public class Pricing {
    private static Float basePrice = 5.f;
    private static Float stdDiscount = 1.f;
    private static Float elderDiscount = 2.f;
    private static Float sunPHSurcharge = 1.f;
    private static Float _3DSurcharge = 0.5f;
    private static Float BBSurcharge = 0.5f;
    private static Float prwSurcharge = 1.5f;
    private static Float classSurcharge = 2.f;

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
