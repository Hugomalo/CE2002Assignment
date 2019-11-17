package MOBLIMA.admin;

import java.io.Serializable;

/**
 * Seat class for seat information (in rows and columns).
 * @author CE2002 SE3 Group 4
 */

public class Seat implements Serializable {
    private static final long serialVersionUID = 1L;

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

    /**
     * Cinema seat with an assigned row and column.
     * @param r Row
     * @param c Column
     */
    Seat(Row r,int c)
    {
        row=r;
        column=c;
    }

    /**
     * Gets the row of a seat.
     * @return Row
     */
    public Row getRow(){return row;}
    /**
     * Gets the column of a seat.
     * @return Column
     */
    public int getColumn(){return column;}
}
