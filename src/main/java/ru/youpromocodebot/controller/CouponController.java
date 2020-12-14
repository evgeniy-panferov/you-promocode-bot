package ru.youpromocodebot.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.service.CouponsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RequestMapping(value = CouponController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CouponController {

    private static final Logger log = LoggerFactory.getLogger(CouponController.class);

    static final String REST_URL = "/coupons";

    private final CouponsService couponsService;

    @GetMapping("/{id}")
    public List<CouponToUser> getForPartnerShipsProgram(@PathVariable String id, @RequestParam String isDatabase) {
        log.info("CouponController getForPartnerShipsProgram id - {}, isDatabase - {}", id, isDatabase);
        return couponsService.getForPartnerShipsProgram(id, Boolean.parseBoolean(isDatabase));
    }
}
