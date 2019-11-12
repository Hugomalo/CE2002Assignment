package admin;

public class Staff{
    private static String login = "Admin";
    private static String password = "Admin";

    protected void setShowtimes(){};
    protected void setMovie(){};

    public static void mainSwitch() {
        System.out.println("Choose an action: ");
    }
    public static boolean login(String lgin, String psw){
        return login.equals(lgin) && password.equals(psw);
    }
}
