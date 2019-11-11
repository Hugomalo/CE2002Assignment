package admin;

import user.Calendar;
import user.Ticket;

public class Pricing {
    private Float basePrice;
    private Float stdDiscount;
    private Float elderDiscount;
    private Float childDiscount;
    private Float sunPHSurcharge;
    private Float _3DSurcharge;
    private Float previewSurcharge;
    private Float classSurcharge;

    public Float priceCalc(Ticket t, Movie m, Calendar cal, Cinema c){return 1f;}
    protected void setBasePrice(Float p){
        basePrice = p;
    }
    protected void setDiscountSurcharge(Float d1, Float d2, Float d3, Float s1, Float s2, Float s3,  Float s4){}
}
