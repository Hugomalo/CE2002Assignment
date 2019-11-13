package admin;

public class CineplexInit {
    public static void init() {
        Cineplex a = new Cineplex("Cathay");
        Cineplex b = new Cineplex("Shaw");
        Cineplex c = new Cineplex("Projector");
        CineplexListing.addCineplex(a);
        CineplexListing.addCineplex(b);
        CineplexListing.addCineplex(c);
    }
}

