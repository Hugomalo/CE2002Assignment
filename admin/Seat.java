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

    private Row row;
    private int column;
    private boolean available;

    Seat(Row r,int c)
    {
        available=true;
        row=r;
        column=c;
    }

    public boolean getAvailable(){
        return  available;
    }

    protected void book(){
        available = false;
    }
}
