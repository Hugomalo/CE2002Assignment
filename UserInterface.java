import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private ArrayList<Booking> bookings;

    public ArrayList<Booking> getBookings(String n) {
        ArrayList<Booking> usrBookings = new ArrayList<Booking>();
        for (int i=0; i<bookings.size(); i++){
            if (bookings.get(i).getName().equals(n)){
                usrBookings.add(bookings.get(i));
            }
        }
        return usrBookings;
    }

    protected void addReview(Movie m){
        Review r = new Review();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your score for the movie " + m.getTitle() + " ?");
        r.rating = sc.nextInt();
        System.out.println("Please tell us more, what did you think of " + m.getTitle() + " ?");
        r.review = sc.nextLine();
        m.addReview(r);
    }

    public void mainSwitch(){
        System.out.println("What action do you want to do ?");
    }
}
