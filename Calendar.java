import java.time.LocalDateTime;

public class Calendar {
    public LocalDateTime selectedDate;
    private LocalDateTime[] SunPH; //Stores sundays and public holidays in an Array

    public LocalDateTime[] getSunPH(){
        return SunPH;
    }
}
