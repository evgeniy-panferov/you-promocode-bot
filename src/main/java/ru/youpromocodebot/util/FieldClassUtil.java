package ru.youpromocodebot.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class FieldClassUtil {

    public static StringBuilder getCommands(Class any) {

        Field[] declaredFields = any.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();

        // Start from 1, because first field is Slf4j
        for (int i = 0; i < declaredFields.length; i++) {
            if(declaredFields[i].getName().equals("log")){
                continue;
            }
            try {
                stringBuilder
                        .append(i)
                        .append(") ")
                        .append(firstCapitalLetter(any.getDeclaredField(declaredFields[i].getName()).get(null)))
                        .append("\n");
            } catch (IllegalAccessException e) {
                log.error("Object can't be create");
            } catch (NoSuchFieldException e) {
                log.error("Field not found");
            }
        }
        return stringBuilder;
    }

    private static StringBuilder firstCapitalLetter(Object object){
        StringBuilder stringBuilder = new StringBuilder(object.toString());
        if (Character.isAlphabetic(stringBuilder.codePointAt(0))) {
            stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        }
        return stringBuilder;
    }
}
