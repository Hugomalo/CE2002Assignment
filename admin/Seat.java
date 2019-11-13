package admin;

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
        I,
        J,

    }
    Seat(Row r,int c)
    {
        available=true;
        row=r;
        column=c;
    }
    private Row row;
    private int column;
    private boolean available;


    public boolean getAvailable(){
        return  available;
    }
    protected void book(){
        available = false;
    }
}
