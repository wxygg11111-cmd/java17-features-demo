package com.example.java17.patternmatching;

import com.example.java17.Demo;
import java.util.List;

/**
 * 演示 Pattern Matching for instanceof（JEP 394，Java 16 正式）。
 * 匹配成功后绑定变量，无需再显式强转。
 */
public class PatternMatchingDemo implements Demo {

    sealed interface Event permits Login, Logout, Message {
    }

    record Login(String user) implements Event {
    }

    record Logout(String user) implements Event {
    }

    record Message(String from, String text) implements Event {
    }

    @Override
    public String title() {
        return "Pattern Matching for instanceof (JEP 394)";
    }

    @Override
    public void run() {
        List<Object> items = List.of("hello", 42, 3.14, new Login("alice"), 100L, "world");
        for (Object item : items) {
            System.out.println(classify(item));
        }
    }

    private String classify(Object obj) {
        if (obj instanceof String s) {
            return "String of length " + s.length() + ": " + s;
        } else if (obj instanceof Integer i) {
            return "Integer, doubled = " + (i * 2);
        } else if (obj instanceof Long l) {
            return "Long, value = " + l;
        } else if (obj instanceof Number n) { // 兜住 Double 等
            return "Other number: " + n;
        } else if (obj instanceof Login login) {
            return "User " + login.user() + " logged in";
        }
        return "Unknown: " + obj;
    }
}
