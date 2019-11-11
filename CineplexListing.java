import java.util.ArrayList;

public class CineplexListing {
    private ArrayList<Cineplex> cineplexes;

    protected void addCineplex(Cineplex c){
        cineplexes.add(c);
    }
    public ArrayList<Cineplex> getCineplexes(){return cineplexes;}
}
