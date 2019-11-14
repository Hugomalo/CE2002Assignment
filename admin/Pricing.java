package admin;

import user.Calendar;
import user.Ticket;

import java.time.LocalDateTime;

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
//    	float prcOfTicket = basePrice;
//    	
//    	if AgeClasses is student or elder {
//    		prcOfTicket -= stdDiscount;
//    	}
//    	else {
//    		prcOfTicket -= elderDiscount;
//    	}
//    	
//    	if MovieTypes is BB,3D or preview {
//			prcOfTicket += _3DSurcharge;
//    	}
//    	else if {
//    		prcOfTicket += BBSurcharge;
//		}
//    	else {
//    		prcOfTicket += prwSurcharge;
//    	}
//    	
//    	if sunPH {
//    		prcOfTicket += sunPHSurcharge;
//    	}
//    	
//    	if cinemaClasses is Gold {
//    		prcOfTicket += classSurcharge;
//    	}
//    	
    	return 1f;
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
