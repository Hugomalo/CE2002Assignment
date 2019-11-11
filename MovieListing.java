import java.util.ArrayList;

public class MovieListing {
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies(){return movies;};
    protected void addMovie(Movie m){
        movies.add(m);
    };

    public void getMoveDetails(String t){
        for (int i=0; i<movies.size(); i++){
            if (movies.get(i).getTitle().equals(t)){
                movies.get(i).showMovieDetails();
                break;
            }
        }
    }
}
