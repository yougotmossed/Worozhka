import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Rules {
    private String line;
    public String message;

    public String getRules(){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            File file = new File("rules.txt");
            file.createNewFile();
            br = new BufferedReader( new FileReader("rules.txt", StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            message = sb.toString();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
