package com.example.java17.streamapi;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamApiDemoTest {

    @Test
    void expandProducesNElements() {
        assertEquals(List.of(), StreamApiDemo.expand(0));
        assertEquals(List.of(10), StreamApiDemo.expand(1));
        assertEquals(List.of(20, 21), StreamApiDemo.expand(2));
        assertEquals(List.of(30, 31, 32), StreamApiDemo.expand(3));
    }

    @Test
    void toListIsUnmodifiable() {
        List<String> upper = Stream.of("java").map(String::toUpperCase).toList();
        assertThrows(UnsupportedOperationException.class, () -> upper.add("X"));
    }
}
