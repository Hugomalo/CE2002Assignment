package admin;

import user.Calendar;
import user.Ticket;

import java.time.LocalDateTime;

public class Pricing {
    private Float basePrice;
    private Float stdDiscount;
    private Float elderDiscount;
    private Float sunPHSurcharge;
    private Float _3DSurcharge;
    private Float BBSurcharge;
    private Float prwSurcharge;
    private Float classSurcharge;

    public static Float priceCalc(Ticket t, Movie m, Cinema c){
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
    protected void setBasePrice(Float p){
        basePrice = p;
    }
    protected void setDiscountSurcharge(Float d1, Float d2, Float s1, Float s2, Float s3, Float s4, Float s5){
    	stdDiscount = d1;
    	elderDiscount = d2;
    	sunPHSurcharge = s1;
    	_3DSurcharge = s2;
    	BBSurcharge = s3;
    	prwSurcharge = s4;
    	classSurcharge = s5;
    }
}
