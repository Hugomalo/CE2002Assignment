import java.time.LocalDateTime;

public class Booking {
    private String name;
    private int phoneNb;
    private String email;
    private String TID;
    private float price;

    public float priceCalc(MovieGoer u, Movie m, Calendar cal, Cinema c){
        float pr = 5;
        this.price = pr;
        return pr;
    }
    public void SetTID(Cinema c){
        LocalDateTime d = LocalDateTime.now();
        TID = String.format(String.valueOf(d.getYear()), d.getMonth(), d.getDayOfMonth(), d.getHour(), d.getMinute(), c.getCineCode());
    }
}
