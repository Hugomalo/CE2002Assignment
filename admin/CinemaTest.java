package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaTest {

   public static void main(String[] args){
        int choice,cineplexChoice,i, newSeatNumber,newCineClass;
        String newcinecode;
        Cineplex a=new Cineplex();
        Cineplex b=new Cineplex();
        Cineplex c=new Cineplex();
        Scanner input= new Scanner(System.in);  // Create a Scanner object
        ArrayList<Cineplex> cineplex = new ArrayList<Cineplex>();
        cineplex.add(a);
        cineplex.add(b);
        cineplex.add(c);

        do {
            System.out.println("what do you want to do? \n 1 to add a cinema in a cineplex 2 to print everything\n");
            choice =input.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("In which cineplex do you want to create a new cinema");
                    cineplexChoice = input.nextInt();
                    System.out.println("how many seats are there?");
                    newSeatNumber = input.nextInt();
                    do {
                        System.out.println("what is the cinema code enter exactly 3 character ");
                        newcinecode = input.next();
                    }while (newcinecode.length()!=3);
                    do {
                        System.out.println("what is the cinema class 1 for Gold ,2 for Normal");
                        newCineClass = input.nextInt();
                    }while (newCineClass<1 || newCineClass>2);
                    switch (cineplexChoice){
                        case 1:
                            System.out.println("cineplex a");
                            a.addCinema(newSeatNumber,newcinecode,newCineClass);
                            break;
                        case 2:
                            System.out.println("cineplex b");
                            b.addCinema(newSeatNumber,newcinecode,newCineClass);
                            break;
                        case 3:
                            System.out.println("cineplex c");
                            c.addCinema(newSeatNumber,newcinecode,newCineClass);
                            break;

                    }
                    break;
                case 2:
                    System.out.println("a cinemas:" );
                    for (i=0;i<a.getCinemaNb();i++){
                        a.getCinemaInfo(i);
                    }
                    System.out.println("b cinemas:" );
                    for (i=0;i<b.getCinemaNb();i++){
                        b.getCinemaInfo(i);
                    }
                    System.out.println("c cinemas:" );
                    for (i=0;i<c.getCinemaNb();i++){
                        c.getCinemaInfo(i);
                    }
            }
        }while (choice!=0);
    }
}