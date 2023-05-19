import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    private int startday;
    private int language;
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

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            execute(sendMessage); //отправка сообщения
        } catch (TelegramApiException e){
            e.printStackTrace(); //если не отправлено, бот выдает ошибку
        }
    }

    public void sendSti(Message message, String stickerId){
        InputFile sticker = new InputFile();
        sticker.setMedia(stickerId);
        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(message.getChatId().toString());
        sendSticker.setSticker(sticker);

        try {
            execute(sendSticker);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public int[] get_zodiak_code(){
        GetMassive getMassive = new GetMassive();
        return getMassive.getZodiak_code();
    }

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
                if (language == 1)
                {
                    sendSti(message, "CAACAgQAAxkBAAEI6MhkWTmv7_8k42nNSNnu-VIFu29lEAACPgwAAp3eWVBGHn6omULBrS8E");
                    sendMsg(message, "Вы лохи, не подходите друг друг");
                }
                if (language == 2)
                {
                    sendSti(message, "CAACAgQAAxkBAAEI6MhkWTmv7_8k42nNSNnu-VIFu29lEAACPgwAAp3eWVBGHn6omULBrS8E");
                    sendMsg(message, "You fools, don't approach each other");
                }
            }
            else {
                if (language == 1) {
                    sendSti(message, "CAACAgQAAxkBAAEI6MhkWTmv7_8k42nNSNnu-VIFu29lEAACPgwAAp3eWVBGHn6omULBrS8E");
                    sendMsg(message, "Ваша совместимость:" + rnd.nextInt(-100, 100) + "%");
                }
                if (language == 2) {
                    sendSti(message, "CAACAgQAAxkBAAEI6MhkWTmv7_8k42nNSNnu-VIFu29lEAACPgwAAp3eWVBGHn6omULBrS8E");
                    sendMsg(message, "Your compatibility:" + rnd.nextInt(-100, 100) + "%");
                }
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
                case "/start", "Змінити мову/Change language" -> {
                    System.out.println("Вибір мови/language selection");
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.enableMarkdown(true);

                    ReplyKeyboardMarkup replyKeyboardMarkup = new
                            ReplyKeyboardMarkup();
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    List<KeyboardRow> keyboard = new ArrayList<>();

                    KeyboardRow keyboardFirstRow = new KeyboardRow();
                    keyboardFirstRow.add("Українська");
                    keyboardFirstRow.add("English");

                    keyboard.add(keyboardFirstRow);
                    replyKeyboardMarkup.setKeyboard(keyboard);

                    sendMessage.setChatId(message.getChatId().toString());
                    sendSti(message, "CAACAgIAAxkBAAEI6PlkWUtwqhGQu_3q5RynqbDMXoCP9AACYhUAAiK6eUnZlk2-IN3yIS8E");
                    sendMessage.setText("Будь ласка, для початку, оберіть мову");
                    sendMsg(message, "Please select a language first");
                    try {
                        execute(sendMessage); //отправка сообщения
                    } catch (TelegramApiException e1){
                        e1.printStackTrace(); //если не отправлено, бот выдает ошибку
                    }
                    zodiak_num = -1;
                }
                case "Українська", "Головне Меню" -> {
                    System.out.println("Ви в головному меню");
                    language = 1;
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.enableMarkdown(true);

                    ReplyKeyboardMarkup replyKeyboardMarkup = new
                            ReplyKeyboardMarkup();
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    List<KeyboardRow> keyboard = new ArrayList<>();

                    KeyboardRow keyboardFirstRow = new KeyboardRow();
                    keyboardFirstRow.add("Гороскоп");
                    keyboardFirstRow.add("Сумісність");
                    KeyboardRow keyboardSecondRow = new KeyboardRow();
                    keyboardSecondRow.add("Змінити мову/Change language");

                    keyboard.add(keyboardFirstRow);
                    keyboard.add(keyboardSecondRow);
                    replyKeyboardMarkup.setKeyboard(keyboard);

                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Ви в головному меню");
                    try {
                        execute(sendMessage); //отправка сообщения
                    } catch (TelegramApiException e1){
                        e1.printStackTrace(); //если не отправлено, бот выдает ошибку
                    }
                    zodiak_num = -2;
                }
                case "English", "Main Menu" -> {
                    System.out.println("You are in main menu");
                    language = 2;
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.enableMarkdown(true);

                    ReplyKeyboardMarkup replyKeyboardMarkup = new
                            ReplyKeyboardMarkup();
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    List<KeyboardRow> keyboard = new ArrayList<>();

                    KeyboardRow keyboardFirstRow = new KeyboardRow();
                    keyboardFirstRow.add("Horoscope");
                    keyboardFirstRow.add("Compatibility");
                    KeyboardRow keyboardSecondRow = new KeyboardRow();
                    keyboardSecondRow.add("Змінити мову/Change language");

                    keyboard.add(keyboardFirstRow);
                    keyboard.add(keyboardSecondRow);
                    replyKeyboardMarkup.setKeyboard(keyboard);

                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("You are in main menu");
                    try {
                        execute(sendMessage); //отправка сообщения
                    } catch (TelegramApiException e1){
                        e1.printStackTrace(); //если не отправлено, бот выдает ошибку
                    }
                    zodiak_num = -2;
                }
                case "Гороскоп" -> {
                    sendMsg(message, "Для того щоб дізнатися свій гороскоп напиши свій знак зодіаку українською, російською або англійською.\n" +
                            "Наприклад: \"Терези\", \"Cancer\", \"стрелец\". ");
                    zodiak_num = -2;
                }
                case "Сумісність" -> {
                    sendMsg(message,"Щоб дізнатися сумісність напишіть два зодіаки або імені через кому (після коми ставте пробіл, Ви ж грамотна людина). \n" +
                            "Наприклад: \"Іван, Маруся\", \"Libra, leo\". \n" +
                            "Бажаю успіху у пророкуванні))");
                    zodiak_num = -2;
                }
                case "Horoscope" -> {
                    sendMsg(message,"In order to recognize your horoscope, write your zodiac sign in Ukrainian, English or Russian.\n" +
                            "For example: \"Терези\", \"Cancer\", \"Стрелец\". ");
                    zodiak_num = -2;
                }
                case "Compability" -> {
                    sendMsg(message,"To find out compatibility, write two zodiac signs or names separated by a comma (put a space after the comma, you are a literate person, after all).\n" +
                            "For example: \"Іван, Маруся\", \"Libra, leo\". \n" +
                            "I wish you success in predicting))");
                    zodiak_num = -2;
                }
                case "Aries", "Овен" -> {
                    System.out.println("Гороскоп для овна");
                    zodiak_num = zodiak_code[0];
                }
                case "Taurus", "Телец", "Телець" -> {
                    System.out.println("Гороскоп для тельца");
                    zodiak_num = zodiak_code[1];
                }
                case "Gemini", "Близнецы", "Близнюки" -> {
                    System.out.println("Гороскоп для близнецов");
                    zodiak_num = zodiak_code[2];
                }
                case "Cancer", "Рак" -> {
                    System.out.println("Горсокоп для раков");
                    zodiak_num = zodiak_code[3];
                }
                case "Leo", "Лев" -> {
                    System.out.println("Гороскоп для львов");
                    zodiak_num = zodiak_code[4];
                }
                case "Virgo", "Дева", "Діва" -> {
                    System.out.println("Гороскоп для дев");
                    zodiak_num = zodiak_code[5];
                }
                case "Libra", "Весы", "Терези" -> {
                    System.out.println("Гороскоп для весов");
                    zodiak_num = zodiak_code[6];
                }
                case "Scorpio", "Скорпион", "Скорпіон" -> {
                    System.out.println("Гороскоп для скорпионов");
                    zodiak_num = zodiak_code[7];
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
                case "Сброс" -> {
                    language = 3;
                }
                default -> zodiak_num=-3;
            }
            if (zodiak_num>=0) {
                FilePredict filePredict = new FilePredict(zodiak_num);
                System.out.println(zodiak_num);
                sendSti(message, "CAACAgQAAxkBAAEI6MhkWTmv7_8k42nNSNnu-VIFu29lEAACPgwAAp3eWVBGHn6omULBrS8E");
                sendMsg(message, filePredict.getPredict());
            }
            else if (zodiak_num == -3){
                if (language == 1) {
                    sendSti(message, "CAACAgIAAxkBAAEI6PlkWUtwqhGQu_3q5RynqbDMXoCP9AACYhUAAiK6eUnZlk2-IN3yIS8E");
                    sendMsg(message, "Помутніння в астралі... Ти мене обманюєш, хотів написати щось інше!");
                    System.out.println("Помутніння в астралі... Ти мене обманюєш, хотів написати щось інше!");
                }
                if (language == 2) {
                    sendSti(message, "CAACAgIAAxkBAAEI6PlkWUtwqhGQu_3q5RynqbDMXoCP9AACYhUAAiK6eUnZlk2-IN3yIS8E");
                    sendMsg(message, "Cloudiness in the astral... You are deceiving me, I wanted to write something else!!!");
                    System.out.println("Cloudiness in the astral... You are deceiving me, I wanted to write something else!!!");
                }
                if (language != 2 && language != 1 && !input.equals("/start"))
                {
                    sendSti(message, "CAACAgIAAxkBAAEI6PlkWUtwqhGQu_3q5RynqbDMXoCP9AACYhUAAiK6eUnZlk2-IN3yIS8E");
                    sendMsg(message, "Before starting to fully use the bot,please select your language. \n" +
                            "Перед початком повноцінного використання бота, будь ласка, оберіть свою мову");
                }
            }
        }
    }
}
