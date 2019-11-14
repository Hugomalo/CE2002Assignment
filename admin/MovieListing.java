package admin;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class MovieListing {
    private static ArrayList<Movie> movies;

    public static Movie getMovie(String title){
        for (Movie m : movies){
            if (m.getTitle().equals(title)){
                return m;
            }
        }
        return null;
    }

    public static void showMovies(){
        for (Movie movie : movies){
            System.out.println(movie.getTitle());
        }
    }

    public static void showRanking(boolean r){
        if (r){
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            for (int i=0; i<rankedMovies.size(); i++){
                if(rankedMovies.get(i).getReviewNb() > 1){
                    rankedMovies.remove(i);
                    i--;
                }
            }
            Sorting.ratingSort(rankedMovies);
            for (int i = 0; i<5; i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getRating() + "/5");
            }
        }
        else{
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            for (int i=0; i<rankedMovies.size(); i++){
                if(rankedMovies.get(i).getReviewNb() > 1){
                    rankedMovies.remove(i);
                    i--;
                }
            }
            Sorting.saleSort(rankedMovies);
            for (int i = 0; i<5; i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getTicketSales() + " tickets");
            }
        }
    }

    protected static void addMovie(Movie m){
        movies.add(m);
    }
    protected static void removeMovie(Movie m){
        movies.remove(m);
    }

    public static void getMovieDetails(String t){
        for (Movie movie : movies) {
            if (movie.getTitle().equals(t)) {
                movie.showMovieDetails();
                break;
            }
        }
    }




}
