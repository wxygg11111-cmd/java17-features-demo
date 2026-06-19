package com.example.java17.switchexpr;

import com.example.java17.switchexpr.SwitchExpressionDemo.Day;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchExpressionDemoTest {

    private final SwitchExpressionDemo demo = new SwitchExpressionDemo();

    @Test
    void dayTypeCoversAllDays() {
        assertEquals("fun day", demo.dayType(Day.MONDAY));
        assertEquals("boring", demo.dayType(Day.TUESDAY));
        assertEquals("hump day", demo.dayType(Day.WEDNESDAY));
        assertEquals("ok", demo.dayType(Day.THURSDAY));
        assertEquals("fun day", demo.dayType(Day.FRIDAY));
        assertEquals("ok", demo.dayType(Day.SATURDAY));
        assertEquals("fun day", demo.dayType(Day.SUNDAY));
    }

    @Test
    void scoreMatchesNameBasedLogic() {
        assertEquals(12, demo.score(Day.MONDAY));    // MONDAY(6) * 2
        assertEquals(7, demo.score(Day.TUESDAY));    // literal
        assertEquals(9, demo.score(Day.WEDNESDAY));  // literal
        assertEquals(8, demo.score(Day.THURSDAY));   // THURSDAY(8)
        assertEquals(12, demo.score(Day.FRIDAY));    // FRIDAY(6) * 2
        assertEquals(8, demo.score(Day.SATURDAY));   // SATURDAY(8)
        assertEquals(12, demo.score(Day.SUNDAY));    // SUNDAY(6) * 2
    }
}
