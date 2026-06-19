package com.example.java17.random;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomDemoTest {

    private static final String ALGO = "L64X128MixRandom";
    private static final long SEED = 17L;

    @Test
    void sameSeedIsReproducible() {
        List<Integer> first = RandomDemo.firstInts(ALGO, SEED, 5);
        List<Integer> again = RandomDemo.firstInts(ALGO, SEED, 5);
        assertEquals(5, first.size());
        assertEquals(first, again);
    }

    @Test
    void differentSeedsProduceDifferentSequences() {
        List<Integer> a = RandomDemo.firstInts(ALGO, SEED, 5);
        List<Integer> b = RandomDemo.firstInts(ALGO, SEED + 1, 5);
        assertNotEquals(a, b);
    }

    @Test
    void boundedIntsStayWithinRange() {
        List<Integer> values = RandomDemo.boundedInts(ALGO, SEED, 100, 10, 20);
        assertEquals(100, values.size());
        for (int value : values) {
            assertTrue(value >= 10 && value < 20, "out of range: " + value);
        }
    }

    @Test
    void algorithmNamesIncludeLxmAndAreSorted() {
        List<String> names = RandomDemo.algorithmNames();
        assertTrue(names.contains(ALGO));
        assertEquals(names.stream().sorted().toList(), names);
    }
}
