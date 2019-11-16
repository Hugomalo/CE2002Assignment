package MOBLIMA.admin;

import java.util.ArrayList;
import java.io.Serializable;
import MOBLIMA.admin.Cineplex;

public class CineplexListing implements Serializable{
    private static final String filepath="listing\\cineplexes.ser";

    protected static ArrayList<Cineplex> cineplexes = (ArrayList<Cineplex>) MOBLIMA.ObjectsIO.ReadObject(CineplexListing.filepath);
    public static String getFilepath(){
        return filepath;
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

    protected static void setName(int i, String name){
        if (cineplexes != null) {
            cineplexes.get(i).setName(name);
        }
    }

    protected static void removeCineplex(Cineplex c){
        cineplexes.remove(c);
    }

    public static ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }
}
