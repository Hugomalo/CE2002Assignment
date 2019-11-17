package MOBLIMA.admin;

import javax.lang.model.type.NullType;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * MovieListing class for the list of movies.
 * @author CE2002 SE3 Group 4
 */

public class MovieListing implements Serializable {
	private static final String filepath = "listing" + File.separator + "movies.ser";
    private static final long serialVersionUID = 1L;

    private static ArrayList<Movie> movies = (ArrayList<Movie>) MOBLIMA.ObjectsIO.ReadObject(MovieListing.filepath);

    /**
     * Gets the filepath of the movie listing.
     * @return Filepath
     */
    public static String getFilepath(){
        return filepath;
    }

    /**
     * Gets a list of movies.
     * @return List of movies
     */
    public static ArrayList<Movie> getMovies(){
        return movies;
    }

    /**
     * Gets the movie based on the title.
     * @param title Movie title
     * @return Movie
     */
    public static Movie getMovie(String title){
        for (Movie m : movies){
            if (m.getTitle().equals(title)){
                return m;
            }
        }
        return null;
    }

    /**
     * Displays a list of all movies.
     */
    public static void showMovies(){
        for (Movie movie : movies){
            System.out.println(movie.getTitle());
        }
    }
    
    /**
     * Displays a list of movies that are not under 'End Of Showing'
     */
    public static void showMoviesShowing(){
        for (Movie movie : movies){
            if (movie.getShowingStatus() != Movie.ShowingStatus.End_Of_Showing) {
                System.out.println(movie.getTitle());
            }
        }
    }

    /**
     * Displays the top 5 ranking movies by either overall rating or ticket sales.
     * @param r Boolean variable
     */
    public static void showRanking(boolean r){
        if (r){
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            for (int i=0; i<rankedMovies.size(); i++){
                if(rankedMovies.get(i).getReviewNb() < 1){
                    rankedMovies.remove(i);
                    i--;
                }
            }
            Sorting.ratingSort(rankedMovies);
            for (int i = 0; i<Integer.min(5, rankedMovies.size()); i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getRating() + "/5");
            }
        }
        else{
            ArrayList<Movie> rankedMovies = new ArrayList<Movie>(movies);
            Sorting.saleSort(rankedMovies);
            for (int i = 0; i<Integer.min(5, rankedMovies.size()); i++){
                System.out.println(rankedMovies.get(i).getTitle() + " : " + rankedMovies.get(i).getTicketSales() + " tickets");
            }
        }
    }

    /**
     * Adds a movie to the list.
     * @param m Movie
     */
    protected static void addMovie(Movie m){
        if (m == null){
            System.out.println("No movies");
        }
        movies.add(m);
    }
    
    /**
     * Removes a movie from the list.
     * @param m Movie
     */
    protected static void removeMovie(Movie m){
        movies.remove(m);
    }

    /**
     * Gets the movie details; if movie title is found, the movie information is displayed.
     * @param t Movie title
     * @return Boolean result; true if movie title is found
     */
    public static boolean getMovieDetails(String t){
        for (Movie movie : movies) {
            if (movie.getTitle().equals(t)) {
                movie.showMovieDetails();
                return true;
            }
        }
        return false;
    }




}
