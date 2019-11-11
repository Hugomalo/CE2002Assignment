public class Movie {
    enum MovieTypes{
        Blockbuster,
        _3D
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
    private String[] cast;
    private Review[] reviews;
    private int reviewNb;
    private float globalRating;
    private int ticketSales;
    private MovieTypes movieType;

    public void addReview(Review r){};
    public String getTitle(){return title;}
    public void showMovieDetails(){
        System.out.println("blabla");
    }
}
