public class Movie {
    enum MovieTypes{

    };
    enum ShowingStatus{
        Coming_Soon,
        Preview,
        Now_Showing,
        End_Of_Showing
    }
    private String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private String[] Cast;
    private Review[] reviews;
    private int reviewNb;
    private int globalRating;
    private int ticketSales;
    private MovieTypes movieType;
}
