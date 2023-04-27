import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {
    private int startday;
    private int[] zodiak_code = get_zodiak_code();

    public void setStartday(int startday) {
        this.startday = startday;
    }


    public String getBotToken() {
        return "5202332329:AAF6yeC4ASl_pKwKCxUbXBNOhaxfRXQEx3g";
    }
    public String getBotUsername() {
        return "vorozhkaBot";
    }

    public void sendMsg(Message message, String text){ //метод для отправки сообщений
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());   // Получаем ID чата
        sendMessage.setText(text); //устанавливаем текст, который отправит бот

        try {
            execute(sendMessage); //отправка сообщения
        } catch (TelegramApiException e){
            e.printStackTrace(); //если не отправлено, бот выдает ошибку
        }
    }
    public int[] get_zodiak_code(){
        GetMassive getMassive = new GetMassive();
        return getMassive.getZodiak_code();
    }

//    static Random random = new Random();
//    public static  int  z=random.nextInt(0, 16);

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println(message.getChat().getFirstName() + " " + message.getChat().getLastName() + " воспользовался ботом");
        String[] massive;
        String input = message.getText();
        input = input.substring(0, 1).toUpperCase() + input.substring(1);
        System.out.println(input);
        Random rnd = new Random();

        GetNowDate getNowDate = new GetNowDate();
        int nowdate = getNowDate.getNow();

        try{
            massive = input.split(", ");
            String first = massive[0];
            String second = massive[1];

            first = first.substring(0, 1).toUpperCase() + first.substring(1);
            second = second.substring(0, 1).toUpperCase() + second.substring(1);

            System.out.println("Совместимость " + first + " и " + second);
            if (first.equals(second))
            {
                sendMsg(message, "Вы лохи, не подходите друг друг");
            }
            else {
                sendMsg(message, "Ваша совместимость:" + rnd.nextInt(-100, 100) + "%");
            }
        } catch(ArrayIndexOutOfBoundsException e) {

            int n=12;
            int zodiak_num = 0;
            if (nowdate != startday) {
                 zodiak_code = new int[n];
                for (int i = 0; i < n; i++) {
                    zodiak_code[i] = rnd.nextInt(0, 16);
                }
                startday = nowdate;
            }
            System.out.println("now +" + nowdate);
            System.out.println("start +" + startday);

            switch (input) {
                case "/start" -> {
                    System.out.println("Бот запущен, читают правила");
                    zodiak_num = -1;
                }
                case "Aries", "Овен" -> {
                    System.out.println("Гороскоп для овна");
                    zodiak_num = zodiak_code[0];
//                    AriesPredict ariesPredict = new AriesPredict();
//                    sendMsg(message, ariesPredict.getAriesPredict());
                }
                case "Taurus", "Телец", "Телець" -> {
                    System.out.println("Гороскоп для тельца");
                    zodiak_num = zodiak_code[1];
//                    TaurusPredict taurusPredict = new TaurusPredict();
//                    sendMsg(message, taurusPredict.getTaurusPredict());
                }
                case "Gemini", "Близнецы", "Близнюки" -> {
                    System.out.println("Гороскоп для близнецов");
                    zodiak_num = zodiak_code[2];
//                    GeminiPredict geminiPredict = new GeminiPredict();
//                    sendMsg(message, geminiPredict.getGeminiPredict());
                }
                case "Cancer", "Рак" -> {
                    System.out.println("Горсокоп для раков");
                    zodiak_num = zodiak_code[3];
//                    CancerPredict cp = new CancerPredict();
//                    sendMsg(message, cp.getCancerPredict());
                }
                case "Leo", "Лев" -> {
                    System.out.println("Гороскоп для львов");
                    zodiak_num = zodiak_code[4];
//                    LeoPredict lp = new LeoPredict();
//                    sendMsg(message, lp.getLeoPredict());
                }
                case "Virgo", "Дева", "Діва" -> {
                    System.out.println("Гороскоп для дев");
                    zodiak_num = zodiak_code[5];
//                    VirgoPredict virgoPredict = new VirgoPredict();
//                  sendMsg(message, virgoPredict.getVirgoPredict());
                }
                case "Libra", "Весы", "Терези" -> {
                    System.out.println("Гороскоп для весов");
                    zodiak_num = zodiak_code[6];
//                    LibraPredict libraPredict = new LibraPredict();
//                    sendMsg(message, libraPredict.getLibraPredict());
                }
                case "Scorpio", "Скорпион", "Скорпіон" -> {
                    System.out.println("Гороскоп для скорпионов");
                    zodiak_num = zodiak_code[7];
//                    ScorpioPredict scorpioPredict = new ScorpioPredict();
//                    sendMsg(message, scorpioPredict.getScorpioPredict());
                }
                case "Sagittarius", "Стрелец", "Стрілець" -> {
                    System.out.println("Гороскоп для стрельцов");
                    zodiak_num = zodiak_code[8];
                }
                case "Capricorn", "Козерог", "Козеріг" -> {
                    System.out.println("Гороскоп для водолеев");
                    zodiak_num = zodiak_code[9];
                }
                case "Aquarius", "Водолей", "Водолій" -> {
                    System.out.println("Гороскоп для водолеев");
                    zodiak_num = zodiak_code[10];
                }
                case "Pisces", "Рыбы", "Риби" -> {
                    System.out.println("Гороскоп для рыб");
                    zodiak_num = zodiak_code[11];
                }
                default -> zodiak_num=-2;
            }
            if (zodiak_num>=0) {
                FilePredict filePredict = new FilePredict(zodiak_num);
                System.out.println(zodiak_num);
                sendMsg(message, filePredict.getPredict());
            } else if (zodiak_num == -1) {
                Rules rules = new Rules();
                sendMsg(message, rules.getRules());
            }
            else if (zodiak_num == -2){
                sendMsg(message, "Помутнение в астрале... Ты меня обманываешь, хотел написать что-то другое!!!");
                System.out.println("Помутнение в астрале... Ты меня обманываешь, хотел написать что-то другое!!!");
            }
        }
    }
}
