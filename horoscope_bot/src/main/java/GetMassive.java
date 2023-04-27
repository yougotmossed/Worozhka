import java.util.Random;

public class GetMassive {
    public int[] getZodiak_code() {
        int n = 12;
        int[] zodiak_code = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            zodiak_code[i] = rnd.nextInt(0, 16);
            System.out.println(zodiak_code[i]);
        }
        return zodiak_code;
    }
}
