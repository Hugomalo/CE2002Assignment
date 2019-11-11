package admin;

import java.util.ArrayList;

public class Cineplex {



    Cineplex(){
        cinemaNb=0;
        cinema = new ArrayList<Cinema>();
    }



    private int cinemaNb;

    private ArrayList<Cinema> cinema ;

    //package admin
    public void addCinema(int seats,String code,int cineClass) {
        Cinema newCinema = new Cinema(seats,code,cineClass);
        cinema.add(newCinema);
        cinemaNb+=1;
    }

    public  int getCinemaNb(){
        return cinemaNb;
    }
    public void getCinemaInfo(int i){
        System.out.println("cine code="+ cinema.get(i).getCineCode());
        System.out.println("cine number of seats="+ cinema.get(i).getNumOfSeat());
        System.out.println("cine Class="+ cinema.get(i).getCinemaClass());

    }

}