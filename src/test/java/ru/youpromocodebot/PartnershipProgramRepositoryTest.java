package ru.youpromocodebot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.youpromocodebot.dao.PartnershipsProgramsDaoImpl;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ru.youpromocodebot.TestData.PROGRAMS_TO_USER_FROM_DB;

class PartnershipProgramRepositoryTest extends AbstractTest {

    @Autowired
    PartnershipsProgramsDaoImpl partnershipsProgramsDao;

    @Test
    void findAll() {
        List<ProgramToUser> all = partnershipsProgramsDao.findAll();
        assertThat(all, containsInAnyOrder(PROGRAMS_TO_USER_FROM_DB.toArray()));
    }
}
