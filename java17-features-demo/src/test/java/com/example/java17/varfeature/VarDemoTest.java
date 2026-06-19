package com.example.java17.varfeature;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VarDemoTest {

    @Test
    void appendWithInferredConcreteIsArrayList() {
        List<String> list = VarDemo.appendWithInferredConcrete();
        assertEquals(List.of("java", "17"), list);
        assertInstanceOf(ArrayList.class, list);
    }

    @Test
    void anonymousGreetingUsesInferredType() {
        assertEquals("Hello, Java 17!", VarDemo.anonymousGreeting());
    }

    @Test
    void firstDoubledReturnsDoubledHead() {
        assertEquals(10, VarDemo.firstDoubled(List.of(5, 10)));
        assertEquals(-1, VarDemo.firstDoubled(List.of()));
    }

    @Test
    void sumOfVarargs() {
        assertEquals(10, VarDemo.sumOf(1, 2, 3, 4));
        assertEquals(0, VarDemo.sumOf());
    }
}
