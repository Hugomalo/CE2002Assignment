package admin;

import java.util.ArrayList;

public class CineplexListing {
    private static ArrayList<Cineplex> cineplexes;
    private static int nbOfCineplexes;

    protected static void addCineplex(Cineplex c){
        cineplexes.add(c);
    }
    public static void showCineplexes(){
        for (Cineplex cineplex : cineplexes){
            System.out.println("[" + (cineplexes.indexOf(cineplex)+1) + "]" + " : " + cineplex.getName());
        }
    }
    public static void showShowtimes(int i){
        cineplexes.get(i).showShowtimes();
    }

    public static int getNbOfCineplexes() {
        return nbOfCineplexes;
    }

    public static Cineplex getCineplex(int i){
        return cineplexes.get(i);
    }
}
