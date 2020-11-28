package ru.youpomocode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.youpomocode.config.TestDataBaseConfig;
import ru.youpromocodebot.config.AppConfig;
import ru.youpromocodebot.dao.PartnershipsProgramsDaoImpl;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static ru.youpomocode.TestData.PROGRAMS_TO_USER_FROM_DB;

@ContextConfiguration(classes = {AppConfig.class, TestDataBaseConfig.class})
@Sql(scripts = {"classpath:db/db-init", "classpath:db/db-populate"}, config = @SqlConfig(dataSource = "dataSource",
        transactionManager = "transactionManager", encoding = "UTF-8"))
class PartnershipProgramRepositoryTest extends AbstractTest {

    @Autowired
    PartnershipsProgramsDaoImpl partnershipsProgramsDao;

    @Test
    void findAll() {
        List<ProgramToUser> all = partnershipsProgramsDao.findAll();
        all.forEach(System.out::println);
        assertThat(all, containsInAnyOrder(PROGRAMS_TO_USER_FROM_DB.toArray()));
    }
}
