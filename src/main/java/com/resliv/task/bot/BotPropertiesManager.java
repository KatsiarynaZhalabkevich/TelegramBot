package com.resliv.task.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Special class to get data from property file
 */
@Component
@PropertySource("classpath:bot.properties")
public class BotPropertiesManager {
    @Value("${bot.userName}")
    private String userName;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.initMessage}")
    private String initMessage;
    @Value("${bot.errorMessage}")
    private String errorMessage;
    @Value("${bot.helpMessage}")
    private String helpMessage;

    public BotPropertiesManager() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInitMessage() {
        return initMessage;
    }

    public void setInitMessage(String initMessage) {
        this.initMessage = initMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }
}
