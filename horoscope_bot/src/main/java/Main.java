import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot = new Bot();
            GetStartDate gsd = new GetStartDate();
            int startday = gsd.getStart();
            bot.setStartday(startday);
            botsApi.registerBot(bot);     // запускаем бота
            System.out.println("Юхууу, мы работаем, девачки!!!");
        } catch (TelegramApiException e) {
            e.printStackTrace(); // если не запускается, выдает ошибку
        }
//        Bot bot = new Bot();
//        GetStartDate gsd = new GetStartDate();
//        int startday = gsd.getStart();
//        bot.setStartday(startday);
//        Random rnd = new Random();
//        int n=12;
//        int[] zodiak_code = new int[12];
//        for (int i = 0; i < n; i++) {
//            zodiak_code[i] = rnd.nextInt(0, 16);
//            System.out.println(zodiak_code[i]);
//            bot.setZodiak_code(zodiak_code[i]);
//
//        }
//        GetMassive getMassive = new GetMassive();
////        getMassive.getZodiak_code();
//        zodiak_code = getMassive.getZodiak_code();




    }
}