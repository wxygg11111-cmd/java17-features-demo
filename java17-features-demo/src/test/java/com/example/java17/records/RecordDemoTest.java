package com.example.java17.records;

import com.example.java17.records.RecordDemo.Point;
import com.example.java17.records.RecordDemo.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordDemoTest {

    @Test
    void pointAccessorsAndEquality() {
        Point p = new Point(3, 4);
        assertEquals(3, p.x());
        assertEquals(4, p.y());
        assertEquals(p, new Point(3, 4));
        assertEquals(p.hashCode(), new Point(3, 4).hashCode());
        assertEquals("Point[x=3, y=4]", p.toString());
    }

    @Test
    void distanceAndTranslate() {
        Point p1 = new Point(3, 4);
        Point p2 = p1.translate(6, 8);
        assertEquals(new Point(9, 12), p2);
        assertEquals(10.0, p1.distanceTo(p2), 1e-9);
    }

    @Test
    void pointRejectsNegativeCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> new Point(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Point(0, -5));
    }

    @Test
    void rangeLengthAndCompare() {
        Range r = new Range(2, 10);
        assertEquals(8, r.length());
        assertTrue(new Range(1, 5).compareTo(new Range(2, 3)) < 0);
        assertTrue(new Range(2, 5).compareTo(new Range(2, 9)) < 0);
        assertEquals(0, new Range(2, 5).compareTo(new Range(2, 5)));
    }

    @Test
    void rangeRejectsInvertedBounds() {
        assertThrows(IllegalArgumentException.class, () -> new Range(5, 2));
    }
}
