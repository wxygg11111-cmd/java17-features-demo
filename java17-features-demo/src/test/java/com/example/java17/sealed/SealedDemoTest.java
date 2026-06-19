package com.example.java17.sealed;

import com.example.java17.sealed.SealedDemo.Circle;
import com.example.java17.sealed.SealedDemo.Rectangle;
import com.example.java17.sealed.SealedDemo.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SealedDemoTest {

    @Test
    void circleArea() {
        assertEquals(Math.PI * 4, new Circle(2).area(), 1e-9);
    }

    @Test
    void rectangleArea() {
        assertEquals(12.0, new Rectangle(3, 4).area(), 1e-9);
    }

    @Test
    void triangleArea() {
        assertEquals(15.0, new Triangle(6, 5).area(), 1e-9);
    }

    @Test
    void describeEachShape() {
        SealedDemo demo = new SealedDemo();
        assertEquals("a circle of radius 2.0", demo.describe(new Circle(2)));
        assertEquals("a rectangle 3.0x4.0", demo.describe(new Rectangle(3, 4)));
        assertEquals("a triangle with base 6.0", demo.describe(new Triangle(6, 5)));
    }
}
