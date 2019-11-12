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

    public Ticket(Float price, AgeClasses ageClass, Showtime selection) {
        this.price = price;
        this.ageClass = ageClass;
        this.selection = selection;
    }

    public Showtime getSelection() {
        return selection;
    }
}
