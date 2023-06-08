package ecommerce.v1;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class Time {
    @Test
    void t() {
        Date date1 = new Date(System.currentTimeMillis() + ((60_000L * 60L * 24L) * 30L));
        System.out.println(date1);
    }
}
