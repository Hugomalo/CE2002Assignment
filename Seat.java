public class Seat {
    enum Row{
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        J,
    }
    private Row row;
    private int column;
    private boolean available;

    protected void book(){
        available = false;
    }
}
