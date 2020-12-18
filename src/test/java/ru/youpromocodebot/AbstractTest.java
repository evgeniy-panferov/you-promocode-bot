package ru.youpromocodebot;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.youpromocodebot.config.AppConfig;
import ru.youpromocodebot.config.TestDataBaseConfig;

@SpringJUnitConfig
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, TestDataBaseConfig.class})
@Sql(scripts = {"classpath:db/test-db-init", "classpath:db/test-db-populate"}, config = @SqlConfig(dataSource = "dataSource",
        transactionManager = "transactionManager", encoding = "UTF-8"))
@ActiveProfiles("test")
abstract class AbstractTest {

    @Test
    void runner() {
    }
}
