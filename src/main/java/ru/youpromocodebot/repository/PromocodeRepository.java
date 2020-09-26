package ru.youpromocodebot.repository;

import ru.youpromocodebot.model.Promocode;

public interface PromocodeRepository {

    Promocode getBy();

    void save();


}
