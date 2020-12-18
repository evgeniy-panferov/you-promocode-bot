package ru.youpromocodebot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.service.PartnershipsProgramsService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

class PartnershipsProgramServiceTest extends AbstractTest {

    @Autowired
    private PartnershipsProgramsService partnershipsProgramService;

    @Test
    void getProgramsFromSites() {
        List<ProgramToUser> programsFromSites = partnershipsProgramService.getProgramsFromSites();
        assertThat(programsFromSites, not(empty()));
    }
}
