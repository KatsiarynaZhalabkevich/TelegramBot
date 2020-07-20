package com.resliv.task.bot;

import com.resliv.task.entity.City;
import com.resliv.task.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Telegram bot class. This class use parameters from bot.properties file with help of
 * BotPropertiesManager class
 */

@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger logger = LogManager.getLogger(Bot.class);
    private static final String START ="/start";
    private static final String HELP ="/help";

    private final CityService service;

    private final String token;
    private final String userName;
    private final String initMessage;
    private final String errorMessage;
    private final String helpMessage;

    @Autowired
    public Bot(BotPropertiesManager manager, CityService service) {
        this.service = service;
        this.token = manager.getToken();
        this.userName = manager.getUserName();
        this.initMessage = manager.getInitMessage();
        this.errorMessage = manager.getErrorMessage();
        this.helpMessage = manager.getHelpMessage();
    }

    /**
     * After Bot initialization method to register the bot
     */
    @PostConstruct
    public void init() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method to react to events from the user side
     *
     * @param update object containing messages from the user and other information
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            switch (text) {
                case START -> sendMsg(message, initMessage);
                case HELP -> sendMsg(message, getHelpMessage());
                default -> sendMsg(message, getCityInfo(message.getText()));
            }

        }
    }

    private String getHelpMessage() {
        List<City> citiesLst = service.getAllCities();
        List<String> citiesNames = citiesLst.stream().map(City::getName).collect(Collectors.toList());
        String cities = citiesNames.stream().map(String::toString).collect(Collectors.joining("\n"));

        return helpMessage + cities;

    }

    /**
     * Getting info about request city
     *
     * @param cityName request city name
     * @return error message if the information is not found
     * or information about the city
     */
    private String getCityInfo(String cityName) {
        City city = service.getCityByName(cityName).orElse(null);
        return city != null ? city.getInfo() : errorMessage;
    }

    /**
     * Method to send a response to user with different attributes
     *
     * @param message an object extracted from Update object to get additional information
     * @param s       message to response (city info, error message or init message)
     */

    private synchronized void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        try {
            setButtons(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Keyboard setting method to make a view for user
     *
     * @param sendMessage response message
     */
    private void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        replyKeyboardMarkup.setKeyboard(createKeyboardRowList());

    }

    private List<KeyboardRow> createKeyboardRowList() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add(new KeyboardButton(HELP));
        firstRow.add(new KeyboardButton(START));

        keyboardRowList.add(firstRow);
        return keyboardRowList;
    }


    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
