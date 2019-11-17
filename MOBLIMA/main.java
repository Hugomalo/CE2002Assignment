package MOBLIMA;

import MOBLIMA.admin.Staff;
import MOBLIMA.user.Calendar;
import MOBLIMA.user.UserInterface;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calendar.initSunPH();
        char choice = 'D';
        do {
            try {
                System.out.println("Please select type of mode: 'A' for admin, 'M' for movie-goer or 'Q' to quit");
                choice = sc.nextLine().charAt(0);
            }catch (Exception e){
                choice = 'D';
            }
            switch (choice) {
                case 'a':
                case 'A': {
                    boolean tryagain = true;
                    do {
                        System.out.println("Please input the login :");
                        String login = sc.nextLine();
                        System.out.println("Please input the password :");
                        String password = sc.nextLine();
                        if (Staff.login(login, password)) {
                            tryagain = false;
                            System.out.println("Welcome to admin mode");
                            Staff.mainSwitch();
                        }
                        else {
                            try {
                                System.out.println("Wrong login or password, do you want to try again ? hit Y for yes, anything for no");
                                tryagain = sc.nextLine().charAt(0) == 'Y';
                            }catch (Exception e){
                                tryagain = false;
                            }
                        }
                    }while (tryagain);
                    break;
                }
                case 'm':
                case 'M':{
                    System.out.println("Welcome to movie goer mode");
                    UserInterface.mainSwitch();
                    break;
                }
                case 'q':
                case 'Q':{
                    break;
                }
                default:{
                    System.out.println("Incorrect input, please try again");
                }
            }
        }while (choice != 'Q');
    }
}
