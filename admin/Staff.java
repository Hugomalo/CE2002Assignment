package admin;

import java.util.ArrayList;
import java.util.Scanner;

public class Staff {
    private static String login = "Admin";
    private static String password = "Admin";

    protected void setShowtimes() {
    }

    ;

    protected void setMovie() {
    }

    ;

    public static void mainSwitch() {


        int choice, i;
        String newCineplexName;
        Cineplex newCineplex;

        Scanner input = new Scanner(System.in);  // Create a Scanner object

        ArrayList<Cineplex> cineplexList = CineplexListing.cineplexes;


        do {
            System.out.println("what do you want to do? \n0 to create a cineplex\n1 to add a cinema in a cineplex \n2 to print everything\n3 to set pricing\n4 to add a movie\n5 to remove a movie\n6 to update a movie");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 0: {
                    System.out.println("what is your cineplex name?");
                    newCineplexName = input.nextLine();
                    newCineplex = new Cineplex(newCineplexName);
                    cineplexList.add(newCineplex);
                    break;
                }
                case 1: {
                    oneNewCinema();
                    break;
                }
                case 2: {
                    StaffPrintingThings.showStuff();
                    break;
                }
                case 3:{
                    setPricing();
                    break;
                }
                case 4:{
                    addMovie();
                    break;
                }
                case 5:{
                    removeMovie();
                    break;
                }
                case 6:{
                    updateMovie();
                    break;
                }
            }
        } while (choice != -1);
    }

    public static boolean login(String lgin, String psw) {
        return login.equals(lgin) && password.equals(psw);
    }


    private static void oneNewCinema() {
        Cinema cine;
        int cineplexChoice;
        Scanner input = new Scanner(System.in);
        System.out.println("In which cineplex do you want to add a cinema?");
        do {
            for (int j = 0; j < CineplexListing.cineplexes.size(); j++) {
                System.out.println((j + 1) + "for cineplex" + CineplexListing.cineplexes.get(j).getName());
            }
            cineplexChoice = input.nextInt();
        } while (cineplexChoice < CineplexListing.cineplexes.size());
        cine = new Cinema(Cineplex.chooseNewCinemaCode(), Cineplex.chooseNewCinemaClass());
        CineplexListing.cineplexes.get(cineplexChoice).addCinema(cine);
    }

    private static void setPricing(){
        Scanner input = new Scanner(System.in);
        Float d1;
        Float d2;
        Float s1;
        Float s2;
        Float s3;
        Float s4;
        Float s5;
        System.out.println("What is the base price of the ticket ?");
        Pricing.setBasePrice(input.nextFloat());
        System.out.println("What is the student discount ?");
        d1 = input.nextFloat();
        System.out.println("What is the senior citizen discount ?");
        d2 = input.nextFloat();
        System.out.println("What is the surcharge for sundays and Public Holidays ?");
        s1 = input.nextFloat();
        System.out.println("What is the surcharge for 3D movies ?");
        s2 = input.nextFloat();
        System.out.println("What is the surcharge for Block Buster movies ?");
        s3 = input.nextFloat();
        System.out.println("What is the surcharge for movie previews ?");
        s4 = input.nextFloat();
        System.out.println("What is the surcharge for gold class cinemas ?");
        s5 = input.nextFloat();
        Pricing.setDiscountSurcharge(d1, d2, s1, s2, s3, s4, s5);
    }
    private static void updateMovie() {
        Scanner input = new Scanner(System.in);
        Movie m = null;
        String title, toChange;
        while (m == null) {
            System.out.println("Here is the movie listing:");
            MovieListing.showMovies();
            System.out.println("What is the title of the movie you want to update ?");
            title = input.nextLine();
            m = MovieListing.getMovie(title);
            if (m != null) {
                {
                    System.out.println("Here is the movie listing:");
                    MovieListing.showMovies();
                    System.out.println("What is the title of the movie you want to update ?");
                    title = input.nextLine();
                    m = MovieListing.getMovie(title);
                    if (m != null) {
                        MovieListing.removeMovie(m);
                        Staff.addMovie();
                    }
                }
            }
        }
    }

    private static void addMovie(){
        Scanner input = new Scanner(System.in);
        String title;
        Movie.ShowingStatus showStat = Movie.ShowingStatus.Now_Showing;
        String synopsis;
        String director;
        ArrayList<String> cast = new ArrayList<>();
        Movie.MovieTypes movieType = Movie.MovieTypes.Classic;
        boolean tryagain = false;
        System.out.println("What is the title of the movie ?");
        title = input.nextLine();
        do {
            try {
                System.out.println("What is the Showing status of the movie ? Chose among:");
                for (Movie.ShowingStatus s : Movie.ShowingStatus.values()) {
                    System.out.println(s);
                }
                showStat = Movie.ShowingStatus.valueOf(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Please input a valid Showing status");
                tryagain = true;
            }
        }while (tryagain);
        tryagain = false;
        System.out.println("What is the synopsis of the movie ?");
        synopsis = input.nextLine();
        System.out.println("Who is the director of the movie ?");
        director = input.nextLine();
        System.out.println("How many actors/actress in the cast ?");
        for (int i=0; i<input.nextInt(); i++){
            input.nextLine();
            System.out.println("Please input actor/actress nb: " + i);
            cast.add(input.nextLine());
        }
        do {
            try {
                System.out.println("What is the movie type ? Chose among:");
                for (Movie.MovieTypes t : Movie.MovieTypes.values()) {
                    System.out.println(t);
                }
                movieType = Movie.MovieTypes.valueOf(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Please input a valid Showing status");
                tryagain = true;
            }
        }while (tryagain);
        MovieListing.addMovie(new Movie(title, showStat, synopsis, director, cast, movieType));
    }

    private  static void removeMovie(){
        Scanner input = new Scanner(System.in);
        int choice;
        String title;
        System.out.println("Do you want to delete a movie (Hit 1) or to change its showing status to End_Of_Showing (Hit 2) ?");
        choice = input.nextInt();
        input.nextLine();
        Movie m = null;
        switch (choice){
            case 1:{
                while (m == null) {
                    System.out.println("Here is the movie listing:");
                    MovieListing.showMovies();
                    System.out.println("What is the title of the movie you want to delete ?");
                    title = input.nextLine();
                    m = MovieListing.getMovie(title);
                    if (m != null){
                        MovieListing.removeMovie(m);
                    }
                }
                break;
            }
            case 2:{
                while (m == null){
                    System.out.println("Here are the movies in showing status");
                }
            }
        }
    }
}
