package admin;

public class Staff{
    String login;
    String password;

    protected void setShowtimes(){};
    protected void setMovie(){};

    public static void mainSwitch() {
        System.out.println("Choose an action: ");
    }
    public boolean login(String login, String password){
        if (login.equals(this.login) && password.equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }
}
