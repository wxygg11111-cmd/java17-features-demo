package com.example.java17.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiscDemoTest {

    @Test
    void hexRoundTrip() {
        byte[] data = {0x01, (byte) 0xFF, 0x4A, 0x7e};
        assertEquals("01ff4a7e", MiscDemo.toHex(data));
        assertArrayEquals(data, MiscDemo.fromHex("01ff4a7e"));
    }

    @Test
    void fromHexAcceptsUppercase() {
        byte[] data = {0x4A, 0x7e};
        assertArrayEquals(data, MiscDemo.fromHex("4A7E"));
    }
}
