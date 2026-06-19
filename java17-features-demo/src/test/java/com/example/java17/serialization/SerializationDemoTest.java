package com.example.java17.serialization;

import com.example.java17.serialization.SerializationDemo.Person;
import java.io.InvalidClassException;
import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializationDemoTest {

    private static final ObjectInputFilter FILTER =
            SerializationDemo.allowPackages("com.example.java17.serialization.", "java.lang.");

    @Test
    void roundTripsAllowedPerson() throws Exception {
        Person original = new Person("Alice", 30);
        byte[] data = SerializationDemo.serialize(original);
        Person back = SerializationDemo.deserialize(data, FILTER);
        assertEquals(original, back);
    }

    @Test
    void rejectsDisallowedArrayList() throws Exception {
        byte[] data = SerializationDemo.serialize(new ArrayList<>(List.of("x")));
        assertThrows(InvalidClassException.class, () -> SerializationDemo.deserialize(data, FILTER));
    }
}
