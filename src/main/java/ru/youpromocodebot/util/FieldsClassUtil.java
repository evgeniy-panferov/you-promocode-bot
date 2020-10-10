package ru.youpromocodebot.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FieldsClassUtil {

    public static StringBuilder getCommands(Class any) {
        log.info("FieldsClassUtil getCommands any - {}", any);

        StringBuilder stringBuilder = new StringBuilder();
        List<String> commands = getCommandsList(any);
        for (int i = 0; i < commands.size(); i++) {
            stringBuilder
                    .append(i)
                    .append(") ")
                    .append(commands.get(i))
                    .append("\n");
        }
        return stringBuilder;
    }

    public static List<String> getCommandsList(Class any) {
        log.info("FieldsClassUtil getCommandsList any - {}", any);

        Field[] declaredFields = any.getDeclaredFields();
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            if (declaredFields[i].getName().equals("log")) {
                continue;
            }
            try {
                commands.add(any.getDeclaredField(declaredFields[i].getName()).get(null).toString());
            } catch (IllegalAccessException e) {
                log.error("Object can't be create");
            } catch (NoSuchFieldException e) {
                log.error("Field not found");
            }
        }
        return commands;
    }

}
