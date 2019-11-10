public class Cinema {
    public char[] getCineCode() {
        return cineCode;
    }

    private void setCineCode(char[] cineCode) {
        this.cineCode = cineCode;
    }

    enum CinemaClasses{

    };
    private CinemaClasses cinemaClass;
    private int numOfSeat;
    private int availableSeat;
    private Seat[] layout;
    private char[] cineCode = new char[3];
}
