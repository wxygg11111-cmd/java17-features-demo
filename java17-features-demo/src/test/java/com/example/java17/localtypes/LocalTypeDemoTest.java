package com.example.java17.localtypes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalTypeDemoTest {

    @Test
    void distanceUsesLocalRecord() {
        assertEquals(5.0, LocalTypeDemo.distance(0, 0, 3, 4), 1e-9);
        assertEquals(10.0, LocalTypeDemo.distance(3, 4, 9, 12), 1e-9);
    }

    @Test
    void describeStatusUsesLocalEnum() {
        assertEquals("OK", LocalTypeDemo.describeStatus(200));
        assertEquals("Not Found", LocalTypeDemo.describeStatus(404));
        assertEquals("Server Error", LocalTypeDemo.describeStatus(500));
    }

    @Test
    void unknownStatusThrows() {
        assertThrows(IllegalArgumentException.class, () -> LocalTypeDemo.describeStatus(999));
    }
}
