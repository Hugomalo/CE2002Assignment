package MOBLIMA.admin;

import MOBLIMA.user.Review;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Movie class for movie information (e.g. type, status, title, synopsis etc).
 * @author CE2002 SE3 Group 4
 */

public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    enum MovieTypes{
        Blockbuster,
        _3D,
        Classic
    };
    enum ShowingStatus{
        Coming_Soon,
        Preview,
        Now_Showing,
        End_Of_Showing
    }
    protected String title;
    protected ShowingStatus showingStatus;
    protected String synopsis;
    protected String director;
    protected ArrayList<String> cast;
    protected ArrayList<Review> reviews;
    protected int reviewNb;
    protected float globalRating;
    protected int ticketSales;
    protected MovieTypes movieType;

    /**
     * Creates a movie with all of its relevant information included, and also creates a new list for storing reviews.
     * @param title Movie title
     * @param showingStatus Showing status of movie (Coming Soon, Preview, Now Showing or End Of Showing)
     * @param synopsis Movie synopsis
     * @param director Movie director
     * @param cast Movie cast
     * @param movieType Type of movie (Blockbuster, 3D or Classic)
     */
    public Movie(String title, ShowingStatus showingStatus, String synopsis, String director, ArrayList<String> cast, MovieTypes movieType) {
        this.title = title;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieType = movieType;
        this.reviewNb = 0;
        this.globalRating = 0;
        this.ticketSales = 0;
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Compares the rating between 2 movies.
     * @param o Object
     * @return Integer denoting which movie has a higher/lower rating
     */
    public int compareToRating(Object o){
        if (o instanceof Movie){
            Movie c = (Movie) o;
            if (getRating() < c.getRating()){
                return 1;
            }
            else if (getRating() > c.getRating()){
                return -1;
            }
            else if (getTicketSales() != (c.getTicketSales())){
                if(getTicketSales() < getTicketSales()){
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if (getTitle().compareTo(c.getTitle()) != 0){
                return getTitle().compareTo(c.getTitle());
            }
            else{
                return 0;
            }
        }
        return 0;
    }

    /**
     * Compares the ticket sales between 2 movies.
     * @param o Object
     * @return Integer denoting which movie has higher/lower sales
     */
    public int compareToSale(Object o){
        if (o instanceof Movie){
            Movie c = (Movie) o;
            if (getTicketSales() < c.getTicketSales()){
                return 1;
            }
            else if (getTicketSales() > c.getTicketSales()){
                return -1;
            }
            else if (!getRating().equals(c.getRating())){
                if(getRating() < getRating()){
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if (getTitle().compareTo(c.getTitle()) != 0){
                return getTitle().compareTo(c.getTitle());
            }
            else{
                return 0;
            }
        }
        return 0;
    }

    /**
     * Gets the movie title.
     * @return Movie title
     */
    public String getTitle(){return title;}
    /**
     * Gets the overall rating of the movie.
     * @return Movie overall rating
     */
    public Float getRating(){return globalRating;}
    /**
     * Gets the ticket sales of the movie.
     * @return Movie ticket sales
     */
    public int getTicketSales(){return ticketSales;}
    /**
     * Gets the showing status of the movie.
     * @return Movie showing status
     */
    protected ShowingStatus getShowingStatus(){return showingStatus;}
    /**
     * Gets the number of reviews for the movie.
     * @return Number of reviews
     */
    protected int getReviewNb(){return reviewNb;}
    /**
     * Gets the type of movie.
     * @return Movie type
     */
    public MovieTypes getType() {return movieType;}
   
    /**
     * Updates the showing status of a movie.
     * @param sh Showing status
     */
    protected void updateShowingStatus(ShowingStatus sh){
        showingStatus = sh;
    }

    /**
     * Adds the number of tickets sold to the ticket sales.
     * @param nbOfTicket Number of tickets sold
     */
    public void addSales(int nbOfTicket) {
        ticketSales += nbOfTicket;
    }

    /**
     * Adds a review to a movie.
     * @param r Review
     */
    public void addReview(Review r){
        reviews.add(r);
        globalRating = (globalRating*reviewNb + r.getRating())/(reviewNb+1);
        reviewNb += 1;
    }

    /**
     * Displays the information of a movie.
     */
    public void showMovieDetails(){
        System.out.println("Title: " + title);
        System.out.println("Status: " + showingStatus);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.print("Cast:");
        for(int i=0; i<cast.size(); i++){
            System.out.printf(" %s", cast.get(i));
        }
        System.out.println("\nMovie type: " + movieType);
        System.out.println("Ticket sales: " + ticketSales);
        if (reviewNb > 1) {
            System.out.println("Global rating: " + globalRating + "/5");
        }
        else{
            System.out.println("Global rating: NA");
        }
        System.out.println("Reviews: ");
        if (reviews.size() > 0) {
            for (Review review : reviews) {
                System.out.print(review.getRating() + "/5 : ");
                System.out.println(review.getReview());
            }
        }
        else{
            System.out.println("NA");
        }
    }
}
