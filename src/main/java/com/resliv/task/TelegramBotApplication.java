package com.resliv.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * Main class to start the app
 */
@SpringBootApplication
@EnableJpaRepositories
public class TelegramBotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
