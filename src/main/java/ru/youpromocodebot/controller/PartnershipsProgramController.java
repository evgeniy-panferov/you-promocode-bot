package ru.youpromocodebot.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.service.PartnershipsProgramsService;

import java.util.List;

@RestController
@CrossOrigin(value = "https://tg-bot-site.herokuapp.com/")
@AllArgsConstructor
@RequestMapping(value = PartnershipsProgramController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnershipsProgramController {
    static final String REST_URL = "api/partnerships-programs";

    private static final Logger log = LoggerFactory.getLogger(PartnershipsProgramController.class);

    private final PartnershipsProgramsService partnershipsProgramsService;

    @GetMapping
    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramController getProgramsFromSites");
        return partnershipsProgramsService.getProgramsFromSites();
    }
}
