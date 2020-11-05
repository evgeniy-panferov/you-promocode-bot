package ru.youpromocodebot.util;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateParse {

    @SneakyThrows
    public static String getStringDate(LocalDateTime localDateTime) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm")
                .optionalStart()
                .appendPattern(":ss")
                .optionalEnd()
                .toFormatter();

        LocalDateTime parse = LocalDateTime.parse(localDateTime.toString(), formatter);

        DateTimeFormatter textFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return parse.format(textFormatter);
    }
}
