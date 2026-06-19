package com.example.java17.patternmatching;

import com.example.java17.patternmatching.PatternMatchingDemo.Login;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternMatchingDemoTest {

    private final PatternMatchingDemo demo = new PatternMatchingDemo();

    @Test
    void classifiesString() {
        assertEquals("String of length 5: hello", demo.classify("hello"));
    }

    @Test
    void classifiesInteger() {
        assertEquals("Integer, doubled = 84", demo.classify(42));
    }

    @Test
    void classifiesLong() {
        assertEquals("Long, value = 100", demo.classify(100L));
    }

    @Test
    void classifiesDoubleAsOtherNumber() {
        assertTrue(demo.classify(3.14).startsWith("Other number: "));
    }

    @Test
    void classifiesLoginEvent() {
        assertEquals("User alice logged in", demo.classify(new Login("alice")));
    }
}
