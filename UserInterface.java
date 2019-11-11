import java.util.ArrayList;

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

    protected void addReview(){

    }

    public void mainSwitch(){
        System.out.println("What action do you want to do ?");

    }
}
