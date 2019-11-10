public class Booking {
    private String name;
    private int phoneNb;
    private String email;
    private String TID;
    private float price;

    private float priceCalc(MovieGoer u, Movie m, Calendar cal, Cinema c){
        float pr = 5;
        this.price = pr;
        return pr;
    }
}
