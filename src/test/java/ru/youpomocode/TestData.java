package ru.youpomocode;

import ru.youpromocodebot.model.Campaign;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestData {

    public static final Coupon COUPON_TEST_DB_ID_0 = new Coupon(
            0, "Скидка 100500", "Active", new Campaign(), "Скидки для нищебродов",
            Collections.singletonList("RU"), "100500%", "Action", "Не требуется",
            "bitchshopframe.ru", "bitchshopgoto.ru", "Скидки для нищебродов",
            LocalDateTime.of(2020, Month.OCTOBER, 19, 10, 00, 00),
            LocalDateTime.of(2020, Month.OCTOBER, 22, 10, 00, 00),
            "classpath:image/photo_not_found.jpg", true);

    public static final Coupon COUPON_TEST_DB_ID_1 = new Coupon(1, "Скидка1 100500", "Active", new Campaign(), "Скидки для нищебродов",
            Collections.singletonList("RU"), "100500%", "Action", "Не требуется",
            "bitchshopframe1.ru", "bitchshopgoto1.ru", "Скидки для нищебродов",
            LocalDateTime.of(2020, 10, 19, 10, 00, 00),
            LocalDateTime.of(2020, 10, 22, 10, 00, 00),
            "classpath:image/photo_not_found.jpg", true);


    public static final List<Coupon> COUPONS_DB = new ArrayList<>() {{
        add(COUPON_TEST_DB_ID_0);
        add(COUPON_TEST_DB_ID_1);
    }};

    public static Coupon getNewCouponDbEntity() {
        return new Coupon(null, "новый Скидка 100500", "Active", new Campaign(), "Новый купон",
                Collections.singletonList("RU"), "100500%", "Action", "Не требуется",
                "new.ru", "new.ru", "Новый купон", LocalDateTime.of(2020, Month.OCTOBER, 19, 10, 10, 10),
                LocalDateTime.of(2020, Month.OCTOBER, 22, 10, 10, 10),
                "classpath:image/photo_not_found.jpg", true);
    }

    public static final ProgramToUser PROGRAM_TO_USER_TEST_DB =
            new ProgramToUser(0, "Бич шоп", "classpath:image/photo_not_found.jpg", "active", true);
    public static final ProgramToUser PROGRAM_TO_USER_TEST_DB1 =
            new ProgramToUser(1, "Бич шоп1", "classpath:image/photo_not_found.jpg", "active", true);
    public static final ProgramToUser PROGRAM_TO_USER_TEST_DB2 =
            new ProgramToUser(2, "Бич шоп2", "classpath:image/photo_not_found.jpg", "active", true);

    public static final List<ProgramToUser> PROGRAMS_TO_USER_FROM_DB = new ArrayList<>() {{
        add(PROGRAM_TO_USER_TEST_DB);
        add(PROGRAM_TO_USER_TEST_DB1);
        add(PROGRAM_TO_USER_TEST_DB2);
    }};

    public static ProgramToUser getNewProgramToUserDbEntity() {
        return new ProgramToUser(null, "Бич шоп", "classpath:image/photo_not_found.jpg", "active", true);
    }
}
