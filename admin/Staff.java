package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff{
    private static String login = "Admin";
    private static String password = "Admin";

    protected void setShowtimes(){};
    protected void setMovie(){};

    public static void mainSwitch() {


        int choice,i;
        String  newCineplexName;
        Cineplex newCineplex;

        Scanner input= new Scanner(System.in);  // Create a Scanner object

        ArrayList<Cineplex> cineplexList= CineplexListing.cineplexes;


        do {
            System.out.println("what do you want to do? \n0 to create a cineplex\n1 to add a cinema in a cineplex \n2 to print everything\n");
            choice =input.nextInt();
            input.nextLine();
            switch (choice)
            {
                case 0:
                    System.out.println("what is your cineplex name?");
                    newCineplexName = input.nextLine();
                    newCineplex = new Cineplex(newCineplexName);
                    cineplexList.add(newCineplex);
                    break;
                case 1:
                    oneNewCinema();
                    break;
                case 2:
                    StaffPrintingThings.showStuff();
                    break;
            }
        }while (choice!=-1);
    }
    public static boolean login(String lgin, String psw){
        return login.equals(lgin) && password.equals(psw);
    }


    public static void oneNewCinema(){
        Cinema cine;
        int cineplexChoice;
        Scanner input= new Scanner(System.in);
        System.out.println("In which cineplex do you want to add a cinema?");
        do {
            for (int j = 0;j<CineplexListing.cineplexes.size();j++){
                System.out.println((j+1)+"for cineplex"+CineplexListing.cineplexes.get(j).getName());
            }
            cineplexChoice=input.nextInt();
        }while(cineplexChoice<CineplexListing.cineplexes.size());
        cine = new Cinema(Cineplex.chooseNewCinemaCode(),Cineplex.chooseNewCinemaClass());
        CineplexListing.cineplexes.get(cineplexChoice).addCinema(cine);

    }

}
