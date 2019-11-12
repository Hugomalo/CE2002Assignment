package admin;

import java.util.ArrayList;

public class Cineplex {



    Cineplex(){
        cinemaNb=0;
        cinemas = new ArrayList<Cinema>();
    }


    private String name;
    private int cinemaNb;
    private ArrayList<Cinema> cinemas;

    //package admin
    public void addCinema(int seats,String code,int cineClass) {
        Cinema newCinema = new Cinema(seats,code,cineClass);
        cinemas.add(newCinema);
        cinemaNb+=1;
    }

    public String getName(){return name;}
    public  int getCinemaNb(){
        return cinemaNb;
    }
    public void getCinemaInfo(int i){
        System.out.println("cine code="+ cinemas.get(i).getCineCode());
        System.out.println("cine number of seats="+ cinemas.get(i).getNumOfSeat());
        System.out.println("cine Class="+ cinemas.get(i).getCinemaClass());

    }

    public void showShowtimes(){
        for(Cinema cine : cinemas){
            System.out.println("Cinema " + cine.getCineCode() + ":");
            cine.showShowtimes();
        }
    }

    public ArrayList<Cinema> getCinemas(){
        return cinemas;
    }

    public ArrayList<String> getMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        for (Cinema cine : cinemas){
            for (Showtime s : cine.getShowtimes()){
                if (!movies.contains(s.getMovie().getTitle()) && (s.getMovie().getShowingStatus() !=  Movie.ShowingStatus.End_Of_Showing)) {
                    movies.add(s.getMovie().getTitle());
                }
            }
        }
        return movies;
    }

}