package admin;

import java.util.ArrayList;

public class CineplexListing {

    protected static ArrayList<Cineplex> cineplexes=new ArrayList<Cineplex>();

    protected static void addCineplex(Cineplex c){
        cineplexes.add(c);
    }

    public static void showCineplexes(){
        if(cineplexes != null) {
            for (Cineplex cineplex : cineplexes) {
                System.out.println("[" + (cineplexes.indexOf(cineplex) + 1) + "]" + " : " + cineplex.getName());
            }
        }
        else{
            System.out.println("No cineplex to show");
        }
    }

    public static void showShowtimes(int i) {
        if (cineplexes != null) {
            cineplexes.get(i).showShowtimes();
        }
    }

    public static int getNbOfCineplexes() {
        return cineplexes.size();
    }

    public static Cineplex getCineplex(int i){
        if (cineplexes != null) {
            return cineplexes.get(i);
        }
        else{return null;}
    }
}
