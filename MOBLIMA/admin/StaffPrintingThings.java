package MOBLIMA.admin;

import java.util.Scanner;

class StaffPrintingThings {
    static void showStuff(){
        int choice;
        Scanner input = new Scanner(System.in);
        do{
            do {
                System.out.println("Do you want to have info on: \n1 Cineplexes  \n2 Have a list of movies\n 0 Quit");
                while (!input.hasNextInt()) {
                    input.nextLine(); //clear the invalid input before prompting again
                    System.out.print("Please enter a positive integer between 0 and 2  ");
                }
                choice = input.nextInt();
            }while(choice<0|| choice >2);
            switch (choice)
            {
                case 0:
                    break;
                case 1:{
                    System.out.println("Here are the cineplexes:");
                    CineplexListing.showCineplexes();
                    System.out.println("Do you want to know the films or the different cinemas in this cineplex? Enter it's index or 0 to quit");
                    int subchoice =input.nextInt();
                    if(subchoice!=0)
                    {
                        CineplexListing.showShowtimes(subchoice-1);
                    }
                    break;
                }
                case 2:
                    MovieListing.showMovies();
                    break;
            }
        }while(choice!=0);
    }

    
}
