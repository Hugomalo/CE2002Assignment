package user;

public class Ticket {
    enum AgeClasses{
        adult,
        senior_citizen,
        child,
        student
    }

    private Float price;
    private AgeClasses ageClass;
}
