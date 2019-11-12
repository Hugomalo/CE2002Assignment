package admin;

import user.Review;
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

    public String getTitle(){return title;}
    public Float getRating(){return globalRating;}
    public int getTicketSales(){return getTicketSales();}


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

    public void addReview(Review r){
        reviews.add(r);
        globalRating = (globalRating*reviewNb + r.getRating())/(reviewNb+1);
        reviewNb += 1;
    }
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
        System.out.print("\nReviews :");
        for(int i=0; i<reviews.size(); i++){
            System.out.println(reviews.get(i).getRating() + "/5 : ");
            System.out.print(reviews.get(i).getReview());
        }
    }

    protected ShowingStatus getShowingStatus(){return showingStatus;}
}
