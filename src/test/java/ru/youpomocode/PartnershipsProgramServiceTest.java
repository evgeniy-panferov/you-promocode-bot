package ru.youpomocode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.youpomocode.config.TestDataBaseConfig;
import ru.youpromocodebot.config.AppConfig;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.service.PartnershipsProgramsService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@ContextConfiguration(classes = {AppConfig.class, TestDataBaseConfig.class})
@Sql(scripts = {"classpath:db/db-init", "classpath:db/db-populate"}, config = @SqlConfig(dataSource = "dataSource",
        transactionManager = "transactionManager", encoding = "UTF-8"))
class PartnershipsProgramServiceTest extends AbstractTest {

    @Autowired
    private PartnershipsProgramsService partnershipsProgramService;

    @Test
    void getProgramsFromSites() {
        List<ProgramToUser> programsFromSites = partnershipsProgramService.getProgramsFromSites();
        assertThat(programsFromSites, not(empty()));
    }
}
