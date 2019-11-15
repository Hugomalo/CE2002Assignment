package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class Cineplex {

    private String name;
    private int nbOfCinema;
    private ArrayList<Cinema> cinemas;

    Cineplex(String n){
        nbOfCinema=0;
        cinemas = new ArrayList<Cinema>();
        name=n;
        threeNewCinema();
    }

    protected void addCinema(Cinema cine) {
        cinemas.add(cine);
        nbOfCinema+=1;
    }

    public String getName(){return name;}
    public void setName(String n){name = n;}

    public  int getCinemaNb(){
        return nbOfCinema;
    }

    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    public ArrayList<String> getMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        if (cinemas != null) {
            for (Cinema cine : cinemas) {
                if (cine.getShowtimes() != null) {
                    for (Showtime s : cine.getShowtimes()) {
                        if (!movies.contains(s.getMovie().getTitle()) && (s.getMovie().getShowingStatus() != Movie.ShowingStatus.End_Of_Showing)) {
                            movies.add(s.getMovie().getTitle());
                        }
                    }
                }
            }
            return movies;
        }
        else {return null;}
    }

    protected Cinema getCinema(String code){
        for (Cinema c : cinemas) {
            if (c.getCineCode().equals(code)){
                return c;
            }
        }
        return null;
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

    protected void addShowtime(String cineCode, Showtime s){
        for (Cinema c : cinemas){
            if (c.getCineCode().equals(cineCode)){
                c.addShowtime(s);
            }
        }
    }
}


