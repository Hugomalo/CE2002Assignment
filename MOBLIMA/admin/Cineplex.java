package MOBLIMA.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Cineplex implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int nbOfCinema;
    private ArrayList<Cinema> cinemas;

    Cineplex(String n){
        nbOfCinema=0;
        cinemas = new ArrayList<Cinema>();
        name=n;
        threeNewCinema();
    }

    void addCinema(Cinema cine) {
        cinemas.add(cine);
        nbOfCinema+=1;
    }

    public String getName(){return name;}

    void setName(String n){name = n;}

    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    public ArrayList<String> getMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                if (cine.getShowtimes() != null) {
                    for (Showtime s : cine.getShowtimes()) {
                        boolean in = movies.contains(s.getMovie().getTitle());
                        String title = s.getMovie().title;
                        boolean EOS = (MovieListing.getMovie(title).getShowingStatus().equals(Movie.ShowingStatus.End_Of_Showing) || MovieListing.getMovie(title).getShowingStatus().equals(Movie.ShowingStatus.Coming_Soon));
                        if (!in && !EOS) {
                            movies.add(s.getMovie().getTitle());
                        }
                    }
                }
            }
            return movies;
        }
        else {return null;}
    }

    Cinema getCinema(String code){
        for (Cinema c : cinemas) {
            if (c.getCineCode().equals(code)){
                return c;
            }
        }
        return null;
    }

    void showShowtimes(){
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

    private void threeNewCinema(){
        Cinema cine;
        System.out.println("You need to add 3 new cinema when creating a new cineplex");
        for (int i = 0;i<3;i++) {
            cine = new Cinema(chooseNewCinemaCode(),chooseNewCinemaClass());
            this.addCinema(cine);
        }
    }
    static String chooseNewCinemaCode(){
        String newCineCode;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("what is the cinema code enter exactly 3 character ");
            newCineCode = input.next();
        } while (newCineCode.length() != 3);
        return newCineCode;
    }

    static int chooseNewCinemaClass() {
     int newCineClass;
     Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("what is the cinema class 1 for Gold, 2 for Normal");
                newCineClass = Integer.parseInt(input.nextLine());
            }catch (Exception e){
                newCineClass = 0;
                System.out.println("Please input a valid entry");
            }
        } while (newCineClass < 1 || newCineClass > 2);
        return newCineClass;
    }

    void addShowtime(String cineCode, Showtime s){
        for (Cinema c : cinemas){
            if (c.getCineCode().equals(cineCode)){
                c.addShowtime(s);
                System.out.println("Added successfully");
            }
        }
    }

    void removeCine(Cinema c){
        cinemas.remove(c);
    }
}


