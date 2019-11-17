package MOBLIMA.admin;

import MOBLIMA.user.Review;

import java.io.Serializable;
import java.util.ArrayList;

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
    String title;
    private ShowingStatus showingStatus;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private ArrayList<Review> reviews;
    private int reviewNb;
    private float globalRating;
    private int ticketSales;
    private MovieTypes movieType;

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


    int compareToRating(Object o){
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
    int compareToSale(Object o){
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

    public String getTitle(){return title;}
    Float getRating(){return globalRating;}
    int getTicketSales(){return ticketSales;}
    ShowingStatus getShowingStatus(){return showingStatus;}
    int getReviewNb(){return reviewNb;}

    MovieTypes getType() {
    	return movieType;
    }
    
    
    void updateShowingStatus(ShowingStatus sh){
        showingStatus = sh;
    }

    public void addSales(int nbOfTicket) {
        ticketSales += nbOfTicket;
    }

    public void addReview(Review r){
        reviews.add(r);
        globalRating = (globalRating*reviewNb + r.getRating())/(reviewNb+1);
        reviewNb += 1;
    }

    void showMovieDetails(){
        System.out.println("Title: " + title);
        System.out.println("Status: " + showingStatus);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.print("Cast:");
        for (String s : cast) {
            System.out.printf(" %s", s);
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
