package com.example.java17.preview;

import com.example.java17.preview.SwitchPatternPreview.Circle;
import com.example.java17.preview.SwitchPatternPreview.Square;
import com.example.java17.preview.SwitchPatternPreview.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchPatternPreviewTest {

    private final SwitchPatternPreview demo = new SwitchPatternPreview();

    @Test
    void formatsPositiveInt() {
        assertEquals("positive int 42", demo.formatValue(42));
    }

    @Test
    void formatsNonPositiveInt() {
        assertEquals("non-positive int 0", demo.formatValue(0));
        assertEquals("non-positive int -5", demo.formatValue(-5));
    }

    @Test
    void formatsString() {
        assertEquals("string \"hi\"", demo.formatValue("hi"));
    }

    @Test
    void formatsShapes() {
        assertEquals("circle r=2.0", demo.formatValue(new Circle(2)));
        assertEquals("square side=3.0", demo.formatValue(new Square(3)));
        assertEquals("triangle area=10.0", demo.formatValue(new Triangle(4, 5)));
    }

    @Test
    void formatsNull() {
        assertEquals("null", demo.formatValue(null));
    }

    @Test
    void formatsDefault() {
        assertEquals("something else: true", demo.formatValue(true));
    }
}
