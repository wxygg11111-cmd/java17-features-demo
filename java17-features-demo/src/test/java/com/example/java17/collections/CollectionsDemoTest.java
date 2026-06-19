package com.example.java17.collections;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsDemoTest {

    @Test
    void immutableListFactory() {
        assertEquals(List.of("java", "kotlin", "scala"), CollectionsDemo.immutableList());
        assertThrows(UnsupportedOperationException.class, () -> CollectionsDemo.immutableList().add("x"));
    }

    @Test
    void immutableSetFactory() {
        assertEquals(3, CollectionsDemo.immutableSet().size());
        assertThrows(UnsupportedOperationException.class, () -> CollectionsDemo.immutableSet().add(4));
    }

    @Test
    void immutableMapFactory() {
        Map<String, Integer> m = CollectionsDemo.immutableMap();
        assertEquals(3, m.size());
        assertEquals(2, m.get("two"));
        assertThrows(UnsupportedOperationException.class, () -> m.put("four", 4));
    }

    @Test
    void teeingAverage() {
        assertEquals(20.0, CollectionsDemo.average(List.of(10, 20, 30)), 1e-9);
        assertEquals(0.0, CollectionsDemo.average(List.of()), 1e-9);
        assertEquals(2.0, CollectionsDemo.average(List.of(1, 2, 3)), 1e-9);
    }

    @Test
    void toUnmodifiableMapOfLengths() {
        Map<String, Integer> lm = CollectionsDemo.lengthMap(List.of("go", "java"));
        assertEquals(2, lm.get("go"));
        assertEquals(4, lm.get("java"));
        assertThrows(UnsupportedOperationException.class, () -> lm.put("x", 1));
    }
}
