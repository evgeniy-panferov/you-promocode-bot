package ru.youpromocodebot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.youpromocodebot.model.Campaign;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudPartnershipsProgramRepository extends JpaRepository<Campaign, Integer> {

    @Query("SELECT c FROM Campaign c")
    List<Campaign> findAll();

}
