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

    public static void getMovieDetails(String t){
        for (Movie movie : movies) {
            if (movie.getTitle().equals(t)) {
                movie.showMovieDetails();
                break;
            }
        }
    }

    public static void showAllMovies()
    {
        Scanner sc = new Scanner(System.in);
        int subChoice;
        do {
            System.out.println("Do you want to see all movies (including not showing) ? Hit 1");
            System.out.println("Do you want to see top 5 movies ranking by ticket sales ? Hit 2");
            System.out.println("Do you want to see top 5 movies ranking by overall reviews ? Hit 3");
            System.out.println("Hit 0 to go back to main");
            subChoice = sc.nextInt();
            sc.nextLine(); // to avoid skipping of next sc instruction
            switch (subChoice){
                case 0:{
                    break;
                }
                case 1:{
                    MovieListing.showMovies();
                    break;
                }
                case 2:{
                    MovieListing.showRanking(false);
                    break;
                }
                case 3:{
                    MovieListing.showRanking(true);
                    break;
                }
                default:{
                    System.out.println("Please input a valid entry");
                    break;
                }
            }
        }while (subChoice != 0);


    }


}
