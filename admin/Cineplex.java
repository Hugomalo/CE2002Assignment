package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class Cineplex {

    private String name;
    private int cinemaNb;
    private ArrayList<Cinema> cinemas;

    Cineplex(String n){
        cinemaNb=0;
        cinemas = new ArrayList<Cinema>();
        name=n;
        threeNewCinema();
    }

    protected void addCinema(Cinema cine) {
        cinemas.add(cine);
        cinemaNb+=1;
    }

    public String getName(){return name;}


    public  int getCinemaNb(){
        return cinemaNb;
    }

    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    public ArrayList<String> getMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                for (Showtime s : cine.getShowtimes()) {
                    if (!movies.contains(s.getMovie().getTitle()) && (s.getMovie().getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                        movies.add(s.getMovie().getTitle());
                    }
                }
            }
            return movies;
        }
        else {return null;}
    }

    protected Cinema getCinema(int i){
        return cinemas.get(i);
    }

    public void showShowtimes(){
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                System.out.println("Cinema " + cine.getCineCode() + ":");
                cine.showShowtimes();
            }
        }
        else{
            System.out.println("No cinema in this cineplex");
        }
    }

    public void showCinemaInfo(int i){
        if (cinemas != null) {
            System.out.println("cine code=" + cinemas.get(i).getCineCode());
            System.out.println("cine number of seats=" + cinemas.get(i).getNumOfSeat());
            System.out.println("cine Class=" + cinemas.get(i).getCinemaClass());
        }
        else{
            System.out.println("No cinema found in this cineplex");
        }

    }

    public void threeNewCinema(){
        Cinema cine;
        System.out.println("You need to add 3 new cinema when creating a new cineplex");
        for (int i = 0;i<3;i++) {
            cine = new Cinema(chooseNewCinemaCode(),chooseNewCinemaClass());
            this.addCinema(cine);
        }
    }
    protected static String chooseNewCinemaCode(){
        String newCineCode;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("what is the cinema code enter exactly 3 character ");
            newCineCode = input.next();
        } while (newCineCode.length() != 3);
        return newCineCode;
    }

    protected static int chooseNewCinemaClass() {
     int newCineClass;
     Scanner input = new Scanner(System.in);
        do {
            System.out.println("what is the cinema class 1 for Gold, 2 for Normal");
            newCineClass = input.nextInt();
        } while (newCineClass < 1 || newCineClass > 2);
        return newCineClass;
    }
}


