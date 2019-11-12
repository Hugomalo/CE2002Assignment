package user;

import admin.Showtime;

public class Ticket {

    enum AgeClasses{
        adult,
        senior_citizen,
        child,
        student
    }

    private Float price;
    private AgeClasses ageClass;
    private Showtime selection;


    public Showtime getSelection() {
        return selection;
    }
}
