package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff{
    private static String login = "Admin";
    private static String password = "Admin";

    protected void setShowtimes(){};
    protected void setMovie(){};

    public static void mainSwitch() {


        int choice,cineplexChoice,i, newSeatNumber,newCineClass;
        String newCineCode;

        Scanner input= new Scanner(System.in);  // Create a Scanner object

        ArrayList<Cineplex> cineplexList= CineplexListing.cineplexes;

        do {
            System.out.println("what do you want to do? \n 1 to add a cinema in a cineplex 2 to print everything\n");
            choice =input.nextInt();
            switch (choice)
            {
                case 1:
                    do {
                        System.out.println("In which cineplex do you want to create a new cinema 1 for "+cineplexList.get(0).getName() +"2 for " + cineplexList.get(1).getName()+"3 for "+cineplexList.get(2).getName());
                        cineplexChoice = input.nextInt();
                    }while(cineplexChoice<1 || cineplexChoice>3);
                    //System.out.println("how many seats are there?");
                    //newSeatNumber = input.nextInt();
                    do {
                        System.out.println("what is the cinema code enter exactly 3 character ");
                        newCineCode = input.next();
                    }while (newCineCode.length()!=3);
                    do {
                        System.out.println("what is the cinema class 1 for Gold ,2 for Normal");
                        newCineClass = input.nextInt();
                    }while (newCineClass<1 || newCineClass>2);
                    System.out.println("cineplex :"+cineplexList.get(cineplexChoice-1).getName());
                    cineplexList.get(cineplexChoice-1).addCinema(newCineCode,newCineClass);
                    break;
                case 2:
                    System.out.println(cineplexList.get(0).getName()+" cinemas:" );
                    for (i=0;i<cineplexList.get(0).getCinemaNb();i++){
                        cineplexList.get(0).showCinemaInfo(i);
                        cineplexList.get(0).getCinema(i).showLayout();
                    }
                    System.out.println(cineplexList.get(1).getName()+" cinemas:" );
                    for (i=0;i<cineplexList.get(1).getCinemaNb();i++){
                        cineplexList.get(1).showCinemaInfo(i);
                    }
                    System.out.println(cineplexList.get(2).getName()+" cinemas:" );
                    for (i=0;i<cineplexList.get(2).getCinemaNb();i++){
                        cineplexList.get(2).showCinemaInfo(i);
                    }
            }
        }while (choice!=0);
    }
    public static boolean login(String lgin, String psw){
        return login.equals(lgin) && password.equals(psw);
    }
}
