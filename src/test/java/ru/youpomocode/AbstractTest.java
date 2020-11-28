package ru.youpomocode;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.youpromocodebot.config.AppConfig;

@SpringJUnitConfig
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
abstract class AbstractTest {

    @Test
    void runner() {
    }
}
