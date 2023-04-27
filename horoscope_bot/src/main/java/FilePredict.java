import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePredict {
    private String line, zodiaknum;
    public String message_predict;

    public FilePredict(int zodiaknum) {
        this.zodiaknum = String.valueOf(zodiaknum);
    }

    public String getPredict(){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            File file = new File("main_predict.txt");
            file.createNewFile();
            br = new BufferedReader( new FileReader(("main_predict.txt")));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                lines.add(line);
            }
//            message_predict = sb.toString();
//            for (String l: lines) {
//                System.out.println("Из списка - " + l);
//            }
            message_predict=lines.get(Integer.parseInt(zodiaknum));
            System.out.println(lines.get(Integer.parseInt(zodiaknum)));

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
        return message_predict;
    }
}
