import java.util.Date;

public class GetStartDate {
    public int getStart(){
        Date date = new Date();
        String day = date.toString();
        System.out.println(day);
        StringBuilder build = new StringBuilder(day);
        build.delete(0,8);
        build.delete(2,21);
        String k = build.toString();
        int startday = Integer.parseInt(k);
        return startday;
    }
}
