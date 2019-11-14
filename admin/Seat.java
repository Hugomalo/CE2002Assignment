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

    Seat(Row r,int c)
    {
        row=r;
        column=c;
    }

    public Row getRow(){return row;}
    public int getColumn(){return column;}
}
