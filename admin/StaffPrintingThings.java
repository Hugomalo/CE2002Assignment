package admin;

import java.util.Scanner;

public class StaffPrintingThings {
    protected static void showStuff(){
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
                case 1:
                    CineplexListing.showCineplexes();
                    break;
                case 2:
                    break;
            }
        }while(choice!=0);
    }
    public static void showStuffinCineplexes(){

    }
}
