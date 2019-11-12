package user;

import admin.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ticket {

    protected Ticket(Float price, AgeClasses age, Showtime selectedST, Cineplex cineplex, Cinema cine) {
        this.price = price;
        ageClass = age;
        selection = selectedST;
        cineplexName = cineplex.getName();
        cineCode = cine.getCineCode();
    }

    enum AgeClasses{
        adult,
        senior_citizen,
        child,
        student
    }

    private Float price;
    private AgeClasses ageClass;
    private Showtime selection;
    private String cineplexName;
    private String cineCode;
    protected void setPrice(Movie m, Cinema cine){
        price = Pricing.priceCalc(this, m, cine);
    }

    protected String getCineCode(){return cineCode;}

    public Showtime getSelection() {
        return selection;
    }
}
