package admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MovieListing {
    private static ArrayList<Movie> movies;

    public static void showMovies(){
        for (Movie movie : movies){
            System.out.println(movie.getTitle());
        }
    }

    public static void showRanking(boolean r){
        if (r){
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            Sorting.ratingSort(rankedMovies);
            for (int i = 0; i<5; i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getRating() + "/5");
            }
        }
        else{
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            Sorting.saleSort(rankedMovies);
            for (int i = 0; i<5; i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getTicketSales() + " tickets");
            }
        }
    }

    protected static void addMovie(Movie m){
        movies.add(m);
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
