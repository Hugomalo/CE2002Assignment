package MOBLIMA.admin;

import java.io.Serializable;

public class Seat implements Serializable {
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
