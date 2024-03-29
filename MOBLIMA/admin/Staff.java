package MOBLIMA.admin;

import MOBLIMA.ObjectsIO;
import MOBLIMA.user.Calendar;
import java.util.*;

/**
 * Staff class for all the functions for the Admin module
 * such as adding/removing/modifying information.
 * @author CE2002 SE3 Group 4
 */

public class Staff {
    private static String login = "Admin";
    private static String password = "Admin";

    /**
     * Main menu and functions for the Admin module of the program.
     */
    public static void mainSwitch() {


        int choice, i;
        String newCineplexName;
        Cineplex newCineplex;

        Scanner input = new Scanner(System.in);  // Create a Scanner object

        ArrayList<Cineplex> cineplexList = CineplexListing.cineplexes;


        do {
            try {
                System.out.println("what do you want to do? \n0 to create a cineplex\n1 to add a cinema in a cineplex \n2 to print everything\n3 to set pricing\n4 to add a movie\n5 to remove a movie\n6 to update a movie\n7 edit showtimes\n8 to edit cineplex/cinema\n9 to add a Public Holiday\n-1 to go back to main");
                choice = input.nextInt();
                input.nextLine();
            }catch (NumberFormatException | InputMismatchException e) {
                choice = 20;
            }
            switch (choice) {
                case 0: {
                    System.out.println("what is your cineplex name?");
                    newCineplexName = input.nextLine();
                    newCineplex = new Cineplex(newCineplexName);
                    cineplexList.add(newCineplex);
                    ObjectsIO.WriteObject(cineplexList, CineplexListing.getFilepath());
                    break;
                }
                case 1: {
                    oneNewCinema();
                    ObjectsIO.WriteObject(cineplexList, CineplexListing.getFilepath());
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
                    ObjectsIO.WriteObject(MovieListing.getMovies(), MovieListing.getFilepath());
                    break;
                }
                case 5:{
                    removeMovie();
                    ObjectsIO.WriteObject(MovieListing.getMovies(), MovieListing.getFilepath());
                    break;
                }
                case 6:{
                    updateMovie();
                    ObjectsIO.WriteObject(MovieListing.getMovies(), MovieListing.getFilepath());
                    break;
                }
                case 7:{
                    int subchoice;
                    do {
                        try {
                            System.out.println("Hit 1 to add showtimes. Hit 2 to remove showtimes. Hit 0 to go back.");
                            subchoice = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            subchoice = -1;
                        }
                        switch (subchoice) {
                            case 0: {
                                break;
                            }
                            case 1: {
                                setShowtimes();
                                ObjectsIO.WriteObject(cineplexList, CineplexListing.getFilepath());
                                break;
                            }
                            case 2: {
                                removeShowtimes();
                                ObjectsIO.WriteObject(cineplexList, CineplexListing.getFilepath());
                                break;
                            }
                            default: {
                                System.out.println("Please input a valid number (i.e. 0, 1 or 2)");
                                break;
                            }
                        }
                    }while (subchoice < 0 || subchoice > 2);
                    break;
                }
                case 8:{
                    updateCineplex();
                    ObjectsIO.WriteObject(cineplexList, CineplexListing.getFilepath());
                    break;
                }
                case 9:{
                    addPH();
                }
                case -1:{
                    break;
                }
                default:{
                    System.out.println("Please input a number among the propositions.");
                }
            }
        } while (choice != -1);
    }

    /**
     * Checks whether the username and password matches the one
     * in the system in order to be granted access to the Admin
     * module.
     * @param lgin Username
     * @param psw Password
     * @return Boolean result showing whether login is successful
     */
    public static boolean login(String lgin, String psw) {
        return login.equals(lgin) && password.equals(psw);
    }

    /**
     * Updates the cineplex information such as cinema code and class, layout.
     */
    private  static void updateCineplex(){
        int subChoice;
        Scanner sc = new Scanner(System.in);
        do{
            try {
                System.out.println("Hit 0 to go back to main");
                System.out.println("Please select the cineplex you want to update :");
                CineplexListing.showCineplexes();
                subChoice = Integer.parseInt(sc.nextLine());
                if (subChoice == 0){
                    break;
                }
                else if (subChoice > 0 && subChoice <= CineplexListing.getNbOfCineplexes()) {
                    int cineplexIndex = subChoice - 1;
                    String cineCode;
                    Cinema c;
                    boolean tryagain = false;
                    System.out.println("Cinemas in " + CineplexListing.getCineplex(cineplexIndex).getName() + " Cineplex");
                    try {
                        for (Cinema cine : CineplexListing.getCineplex(cineplexIndex).getCinemas()) {
                            System.out.println("    " + cine.getCineCode());
                        }
                    }
                    catch (NullPointerException e){
                        System.out.println("No cinema in this cineplex");
                    }
                    do{
                        System.out.println("Please input the Code of the cinema you want to update\nOr key in '-1' to change cineplex name and -2 to delete cineplex");
                        cineCode = sc.nextLine();
                        if (cineCode.equals("-1")){
                            System.out.println("Please input the new name for cineplex " + CineplexListing.getCineplex(cineplexIndex).getName());
                            cineCode = sc.nextLine();
                            CineplexListing.setName(cineplexIndex, cineCode);
                            tryagain = false;
                        }
                        else if (cineCode.equals("-2")){
                            CineplexListing.removeCineplex(CineplexListing.getCineplex(cineplexIndex));
                        }
                        else {
                            c = CineplexListing.cineplexes.get(cineplexIndex).getCinema(cineCode);
                            if (c != null) {
                                int a;
                                try{
                                    do{
                                        System.out.println("Do you want to change:\n[1] The cinema Code\n[2] The cinema class \n[3] The seat layout\n[4] Delete the Cinema form the cineplex\n[0] To go back");
                                        a = sc.nextInt();
                                        sc.nextLine();
                                        switch (a){
                                            case 1:{
                                                c.setCineCode(Cineplex.chooseNewCinemaCode());
                                                tryagain = false;
                                                break;
                                            }
                                            case 2:{
                                                c.setClass(Cineplex.chooseNewCinemaClass());
                                                tryagain = false;
                                                break;
                                            }
                                            case 3:{
                                                boolean b = false;
                                                int numSeat = 0;
                                                do{
                                                    try{
                                                        System.out.println("How many seat is the cinema ? Please input a perfect square");
                                                        numSeat = sc.nextInt();
                                                        sc.nextLine();
                                                        double sr = Math.sqrt(numSeat);
                                                        if (numSeat < 2 || (sr - Math.floor(sr)) != 0){
                                                            System.out.println("Please enter a perfect square greater than 1");
                                                            b = true;
                                                        }
                                                        else{
                                                            c.setLayout(numSeat);
                                                            b = false;
                                                        }
                                                    }catch (NumberFormatException e){
                                                        System.out.println("Please enter a perfect square");
                                                        b = true;
                                                    }
                                                }while (b);
                                                tryagain = false;
                                                break;
                                            }
                                            case 4:{
                                                CineplexListing.getCineplex(cineplexIndex).removeCine(c);
                                            }
                                            case 0:{
                                                tryagain = false;
                                                break;
                                            }
                                            default:{
                                                System.out.println("Please input a valid choice");
                                                tryagain = true;
                                                break;
                                            }
                                        }
                                    }while (tryagain);
                                }catch (NumberFormatException e){
                                    System.out.println("Please input an integer to make your choice");
                                }
                            } else {
                                System.out.println("Please input a valid Cinema Code");
                                tryagain = true;
                            }
                        }
                    }while (tryagain);
                    break;
                }
                else{
                    System.out.println("Please input a number in the range of available index");
                }
            }catch (Exception e){
                System.out.println("Please input a valid entry");
            }
        }while (true);
    }

    /**
     * Adds or changes the showtimes of the cinemas.
     */
    private static void setShowtimes() {
        int subChoice;
        Scanner sc = new Scanner(System.in);
        do{
            try {
                System.out.println("Hit 0 to go back to main");
                System.out.println("To select the cinema you want to change showtime, please select the cineplex first :");
                CineplexListing.showCineplexes();
                subChoice = Integer.parseInt(sc.nextLine());
                if (subChoice == 0){
                    break;
                }
                else if (subChoice > 0 && subChoice <= CineplexListing.getNbOfCineplexes()) {
                    int cineplexIndex = subChoice - 1;
                    String cineCode;
                    Cinema c;
                    System.out.println("Here are current showtimes:");
                    CineplexListing.showShowtimes(cineplexIndex);
                    do{
                        System.out.println("Please input the Code of the cinema of which you want to change showtimes");
                        cineCode = sc.nextLine();
                        c = CineplexListing.cineplexes.get(cineplexIndex).getCinema(cineCode);
                        if (c != null){
                            Movie m;
                            String t;
                            System.out.println("Here are the movies for which you can add a showtime:");
                            MovieListing.showMoviesShowing();
                            do {
                                System.out.println("What is the name of the Movie you want to add ?");
                                t = sc.nextLine();
                                m = MovieListing.getMovie(t);
                                if (m == null){
                                    System.out.println("Please enter a valid title. Hit '0' to abort");
                                }
                                else if (!t.equals("0")) {
                                    int i;
                                    System.out.println("How many showtimes do you want to add for this cinema and Movie ?");
                                    i = sc.nextInt();
                                    sc.nextLine();
                                    for (int j = 0; j < i; j++) {
                                        int month;
                                        int day;
                                        int hour;
                                        int minute;
                                        boolean tryagain = false;
                                        do {
                                            System.out.println("Please input Showtime nb: " + (j + 1));
                                            try {
                                                System.out.println("What is the month for your showtime ? in 2 digit format");
                                                month = Integer.parseInt(sc.nextLine());
                                                System.out.println("What is the day for your showtime ? in 2 digit format");
                                                day = Integer.parseInt(sc.nextLine());
                                                System.out.println("What is the hour for your showtime ? in 2 digit format");
                                                hour = Integer.parseInt(sc.nextLine());
                                                System.out.println("What is the minute for your showtime ? in 2 digit format");
                                                minute = Integer.parseInt(sc.nextLine());
                                                if (month > 12 || month < 1 || day > 31 || day < 1 || hour > 23 || hour < 0 || minute > 59 || minute < 0) {
                                                    System.out.println("Please input a valid date or hour.");
                                                    tryagain = true;
                                                } else {
                                                    Showtime s = new Showtime(m, month, day, hour, minute, c);
                                                    CineplexListing.cineplexes.get(cineplexIndex).addShowtime(cineCode, s);
                                                    tryagain = false;
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println("Please input a valid entry, re-enter the information for the last showtime.");
                                                tryagain = true;
                                            }
                                        } while (tryagain);
                                    }
                                }
                            }while (m == null && !t.equals("0"));
                        }
                        else{
                            System.out.println("Please input a valid Cinema Code. Hit '0' to abort");
                        }
                    }while (c == null && !cineCode.equals("0"));
                    break;
                }
                else{
                    System.out.println("Please input a number in the range of available index");
                }
            }catch (Exception e){
                System.out.println("Please input a valid entry");
            }
        }while (true);
    }

    /**
     * Removes the showtimes of the cinemas.
     */
    private static void removeShowtimes() {
        int subChoice;
        Scanner sc = new Scanner(System.in);
        boolean abort = false;
        do {
            try {
                System.out.println("Hit 0 to go back to main");
                System.out.println("To select the cinema you want to change showtime, please select the cineplex first :");
                CineplexListing.showCineplexes();
                subChoice = Integer.parseInt(sc.nextLine());
                if (subChoice == 0) {
                    abort = true;
                }
                else if (subChoice > 0 && subChoice <= CineplexListing.getNbOfCineplexes()) {
                    int cpIndex = subChoice - 1;
                    String cineCode;
                    Cinema c;
                    String movieTitle;
                    Cineplex cineplex = CineplexListing.getCineplex(cpIndex);
                    System.out.println("Movies showing at " + cineplex.getName());
                    ArrayList<String> movies = new ArrayList<>(cineplex.getMovies());
                    for (String movie : movies) {
                        System.out.println(movie);
                    }
                    do {
                        System.out.println("For what movie do you want to remove a showtime ? : (please input Title)");
                        movieTitle = sc.nextLine();
                        if (movieTitle.equals("0")) {
                            abort = true;
                        } else if (!movies.contains(movieTitle)) {
                            System.out.println("Please input a valid movie Title. Hit 0 to abort");
                        }
                    } while (!movies.contains(movieTitle) && !abort);
                    if (!abort) {
                        System.out.println("Here are the available showtimes for each cinema for" + movieTitle);
                        ArrayList<Cinema> cinemas = new ArrayList<Cinema>(cineplex.getCinemas());
                        Map<Integer, ArrayList<Showtime>> movieST = new HashMap<>();
                        for (Cinema cine : cinemas) {
                            System.out.println("[" + (cinemas.indexOf(cine) + 1) + "]" + cine.getCineCode());
                            movieST.put(cinemas.indexOf(cine), cine.getShowShowtimes(movieTitle));
                        }
                        int cineIndex = -1;
                        Cinema cinema = null;
                        Showtime selectedST = null;
                        boolean tryagain = true;
                        while (tryagain) {
                            try {
                                System.out.println("Please input desired cinema index :");
                                cineIndex = sc.nextInt() - 1;
                                if (cineIndex == -1) {
                                    abort = true;
                                    tryagain = false;
                                } else {
                                    cinema = cinemas.get(cineIndex);
                                    sc.nextLine();
                                    tryagain = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Please input a valid cinema, Hit 0 to abort");
                                tryagain = true;
                            }
                        }
                        tryagain = true;
                        while (tryagain && !abort) {
                            try {
                                System.out.println("Please input the desired showtime at cinema " + cinema.getCineCode());
                                int a = sc.nextInt() - 1;
                                sc.nextLine();
                                if (a == -1) {
                                    abort = true;
                                    tryagain = false;
                                } else {
                                    selectedST = movieST.get(cineIndex).get(a);
                                    tryagain = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Please input a valid index for showtime. Hit 0 to abort");
                                tryagain = true;
                            }
                        }
                        if (abort) {
                            break;
                        } else {
                            cinema.removeShowtime(selectedST);
                        }
                    }
                    else {
                        break;
                    }
                }
            }catch(NumberFormatException e){
                System.out.println("Please input a valid entry");
            }
        } while (!abort);
    }

    /**
     * Adds 1 new cinema to a cineplex.
     */
    private static void oneNewCinema() {
        Cinema cine;
        int cineplexChoice;
        Scanner input = new Scanner(System.in);
        if(CineplexListing.cineplexes.size() == 0){
            System.out.println("No cineplex created");
        }
        else {
            do {
                System.out.println("In which cineplex do you want to add a cinema?");
                for (int j = 0; j < CineplexListing.cineplexes.size(); j++) {
                    System.out.println("[" + (j + 1) + "] : " + CineplexListing.cineplexes.get(j).getName());
                }
                try {
                    cineplexChoice = (Integer.parseInt(input.nextLine()) - 1);
                }catch (NumberFormatException e){
                    System.out.println("Please input a valid number");
                    cineplexChoice = -1;
                }
             } while (cineplexChoice >= CineplexListing.cineplexes.size() || cineplexChoice < 0);
            cine = new Cinema(Cineplex.chooseNewCinemaCode(), Cineplex.chooseNewCinemaClass());
            CineplexListing.cineplexes.get(cineplexChoice).addCinema(cine);
        }
    }

    /**
     * Sets all the pricing information (base, discounts and surcharges) for ticket booking.
     */
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

    /**
     * Updates the information of a movie.
     */
    private static void updateMovie() {
        Scanner input = new Scanner(System.in);
        Movie m = null;
        String title;
        while (m == null) {
            System.out.println("Here is the movie listing:");
            MovieListing.showMovies();
            System.out.println("What is the title of the movie you want to update ?");
            title = input.nextLine();
            m = MovieListing.getMovie(title);
            if (m != null) {
                {
                    m = MovieListing.getMovie(title);
                    if (m != null) {
                        MovieListing.removeMovie(m);
                        System.out.println("please enter the new data of the updated movie");
                        Staff.addMovie();
                    }
                }
            }
        }
    }

    /**
     * Adds a movie to the list of movies.
     */
    private static void addMovie(){
        Scanner input = new Scanner(System.in);
        String title;
        Movie.ShowingStatus showStat = Movie.ShowingStatus.Now_Showing;
        String synopsis;
        String director;
        ArrayList<String> cast = new ArrayList<>();
        Movie.MovieTypes movieType = Movie.MovieTypes.Classic;
        boolean tryagain = true;
        System.out.println("What is the title of the movie ?");
        title = input.nextLine();
        while (tryagain) {
            try {
                System.out.println("What is the Showing status of the movie ? Chose among:");
                for (Movie.ShowingStatus s : Movie.ShowingStatus.values()) {
                    System.out.println(s);
                }
                showStat = Movie.ShowingStatus.valueOf(input.nextLine());
                tryagain = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Please input a valid Showing status");
                tryagain = true;
            }
        }
        System.out.println("What is the synopsis of the movie ?");
        synopsis = input.nextLine();
        System.out.println("Who is the director of the movie ?");
        director = input.nextLine();
        tryagain = true;
        while (tryagain) {
            try {
                System.out.println("How many actors/actress in the cast ?");
                int a;
                a = Integer.parseInt(input.nextLine());
                tryagain = false;
            for (int i = 0; i < a; i++) {
                System.out.println("Please input actor/actress nb: " + (i+1));
                cast.add(input.nextLine());
            }
            }catch (NumberFormatException e){
                System.out.println("Please input a number.");
            }
        }
        do {
            try {
                System.out.println("What is the movie type ? Chose among:");
                for (Movie.MovieTypes t : Movie.MovieTypes.values()) {
                    System.out.println(t);
                    tryagain = false;
                }
                movieType = Movie.MovieTypes.valueOf(input.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Please input a valid Movie Type");
                tryagain = true;
            }
        }while (tryagain);
        MovieListing.addMovie(new Movie(title, showStat, synopsis, director, cast, movieType));
    }

    /**
     * Removes a movie from the list of movies.
     */
    private  static void removeMovie(){
        Scanner input = new Scanner(System.in);
        int choice;
        String title;
        boolean tryagain = true;
        do {
        System.out.println("Do you want to delete a movie (Hit 1) or to change its showing status to End_Of_Showing (Hit 2) or go back to main (Hit 0)?");
        choice = input.nextInt();
        input.nextLine();
        Movie m = null;
            switch (choice) {
                case 1: {
                    System.out.println("Here is the movie listing:");
                    MovieListing.showMovies();
                    System.out.println("What is the title of the movie you want to delete ?");
                    title = input.nextLine();
                    m = MovieListing.getMovie(title);
                    if (m != null) {
                        MovieListing.removeMovie(m);
                        tryagain = false;
                    } else {
                        System.out.println("Please input a valid movie title");
                        tryagain = true;
                    }
                break;
                }
                case 2: {
                    System.out.println("Here are the movies in showing status");
                    MovieListing.showMoviesShowing();
                    System.out.println("What is the title of the movie you want to delete ?");
                    title = input.nextLine();
                    m = MovieListing.getMovie(title);
                    if (m != null) {
                        m.updateShowingStatus(Movie.ShowingStatus.End_Of_Showing);
                        tryagain = false;
                    } else {
                        System.out.println("Please input a valid movie title");
                        tryagain = true;
                    }
                }
                break;
                case 0:{
                    tryagain = false;
                    break;
                }
                default: {
                    System.out.println("Please input a valid entry");
                }
            }
        }while (tryagain);
    }

    /**
     * Adds a public holiday to the program's calendar.
     */
    private static void addPH(){
        Scanner sc = new Scanner(System.in);
        int month;
        int day;
        do{
            try {
                System.out.println("Please input the month of the new public holiday");
                month = Integer.parseInt(sc.nextLine());
                System.out.println("Please input the day of the month of the new public holiday");
                day = Integer.parseInt(sc.nextLine());
                if(month > 12 || month < 1 || day > 31 || day < 1){
                    throw new NumberFormatException();
                }
            }catch (NumberFormatException e){
                System.out.println("Please input a month between 1 and 12 and a day between 1 and 31.");
                month = 0;
                day = 0;
            }
        }while (month < 1);
        try {
            Calendar.addPH(month, day);
        }catch (Exception e){
            System.out.println("This date is not valid. New Public Holiday could not be added");
        }
    }
}
