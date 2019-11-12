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
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>();
            rankedMovies = .sort(movies);
            for (Movie movie : rankedMovies){
                System.out.println(movie.getTitle() + " : " + movie.getRating());
            }
        }
        else{
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>();
            rankedMovies = .sort(movies);
            for (Movie movie : rankedMovies){
                System.out.println(movie.getTitle() + " : " + movie.getTicketSales());
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
