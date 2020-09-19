package ru.youpromocodebot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;


public class BotStarter {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext("ru.youpromocodebot");

    }
}
