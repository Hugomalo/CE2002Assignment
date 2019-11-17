package MOBLIMA.admin;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * CineplexListing class for the list of all cineplexes created.
 * @author CE2002 SE3 Group 4
 */

public class CineplexListing implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String filepath = "listing" + File.separator + "cineplexes.ser";

    static ArrayList<Cineplex> cineplexes = (ArrayList<Cineplex>) MOBLIMA.ObjectsIO.ReadObject(CineplexListing.filepath);

    /**
     * Gets the filepath of the cineplex listing.
     * @return Filepath
     */
    public static String getFilepath(){
        return filepath;
    }

    /**
     * Displays the list of all cineplexes created.
     */
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

    /**
     * Displays the showtimes of the cinemas in all the cineplexes.
     * @param i Index
     */
    public static void showShowtimes(int i) {
        if (cineplexes != null) {
            cineplexes.get(i).showShowtimes();
        }
    }

    /**
     * Gets the total number of cineplexes created.
     * @return Number of cineplexes
     */
    public static int getNbOfCineplexes() {
        return cineplexes.size();
    }

    /**
     * Gets the list of cineplexes.
     * @param i Index
     * @return List of cineplexes
     */
    public static Cineplex getCineplex(int i){
        if (cineplexes != null) {
            return cineplexes.get(i);
        }
        else{return null;}
    }

    /**
     * Sets the name of the cineplex.
     * @param i Index
     * @param name Name of cineplex
     */
    static void setName(int i, String name){
        if (cineplexes != null) {
            cineplexes.get(i).setName(name);
        }
    }

    /**
     * Removes a cineplex from the program.
     * @param c Cineplex
     */
    static void removeCineplex(Cineplex c){
        cineplexes.remove(c);
    }

    /**
     * Gets the list of cineplexes.
     * @return List of cineplexes
     */
    public static ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }
}
