package admin;

import user.Review;
import java.util.ArrayList;

public class Movie {
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


    public int compareToRating(Object o){
        if (o instanceof Movie){
            Movie c = (Movie) o;
            if (getRating() < c.getRating()){
                return -1;
            }
            else if (getRating() > c.getRating()){
                return 1;
            }
            else if (getTicketSales() != (c.getTicketSales())){
                if(getTicketSales() < getTicketSales()){
                    return -1;
                }
                else {
                    return 1;
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
    public int compareToSale(Object o){
        if (o instanceof Movie){
            Movie c = (Movie) o;
            if (getTicketSales() < c.getTicketSales()){
                return -1;
            }
            else if (getTicketSales() > c.getTicketSales()){
                return 1;
            }
            else if (!getRating().equals(c.getRating())){
                if(getRating() < getRating()){
                    return -1;
                }
                else {
                    return 1;
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
    public Float getRating(){return globalRating;}
    public int getTicketSales(){return ticketSales;}
    protected ShowingStatus getShowingStatus(){return showingStatus;}
    protected int getReviewNb(){return reviewNb;}

    protected void updateShowingStatus(ShowingStatus sh){
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

    public void showMovieDetails(){
        System.out.println("Title : " + title);
        System.out.println("Synopsis : " + synopsis);
        System.out.println("Director :" + director);
        System.out.print("Cast :");
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
        System.out.print("\nReviews :");
        for (Review review : reviews) {
            System.out.println(review.getRating() + "/5 : ");
            System.out.print(review.getReview());
        }
    }
}
