import java.util.Date;

public class GetNowDate {
    public int getNow() {
        Date date = new Date();
        String day = date.toString();
        StringBuilder build = new StringBuilder(day);
        date.getTime();
        String now = date.toString();
        build = new StringBuilder(now);
        build.delete(0,8);
        build.delete(2,21);
        String k = build.toString();
        int nowday = Integer.parseInt(k);
        return nowday;
    }
}
