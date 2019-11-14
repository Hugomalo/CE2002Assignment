import admin.Staff;
import user.UserInterface;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char choice = 'D';
        do {
            System.out.println("Do you want to login as staff ? Hit Y for yes and N for No, Q to quit");
            choice = sc.next().charAt(0);
            sc.nextLine();
            switch (choice) {
                case 'Y': {
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
                            System.out.println("Wrong login or password, do you want to try again ? hit Y for yes, anything for no");
                            tryagain = sc.next().charAt(0) == 'Y';
                            sc.nextLine();
                        }
                    }while (tryagain);
                    break;
                }
                case 'N':{
                    System.out.println("Welcome to movie goer mode");
                    UserInterface.mainSwitch();
                    break;
                }
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
