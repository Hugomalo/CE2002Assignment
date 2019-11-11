import java.util.ArrayList;

public class MovieListing {
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies(){return movies;};
    protected void addMovie(Movie m){
        movies.add(m);
    };
}
