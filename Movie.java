import java.sql.SQLOutput;
import java.util.ArrayList;

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
    private ArrayList<String> cast;
    private ArrayList<Review> reviews;
    private int reviewNb;
    private float globalRating;
    private int ticketSales;
    private MovieTypes movieType;

    public void addReview(Review r){};
    public String getTitle(){return title;}
    public void showMovieDetails(){
        System.out.println("Title : " + title);
        System.out.println("Synopsis : " + synopsis);
        System.out.println("Director :" + director);
        System.out.print("\nCast :");
        for(int i=0; i<cast.size(); i++){
            System.out.printf(" %s", cast.get(i));
        }
        System.out.println("Movie type: " + movieType);
        System.out.println("Ticket sales: " + ticketSales);
        System.out.println("Global rating: " + globalRating + "/5");
        System.out.printf("\nReviews :");
        for(int i=0; i<reviews.size(); i++){
            System.out.println(reviews.get(i).rating + "/5 : ");
            System.out.print(reviews.get(i).review);
        }
    }
}
