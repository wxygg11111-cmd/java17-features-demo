package com.example.java17.switchexpr;

import com.example.java17.Demo;

/**
 * 演示 Switch Expressions（JEP 361，Java 14 正式）。
 * 箭头形式无 fall-through，可直接作为表达式返回值，块内用 yield 返回。
 */
public class SwitchExpressionDemo implements Demo {

    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    @Override
    public String title() {
        return "Switch Expressions (JEP 361)";
    }

    @Override
    public void run() {
        for (Day day : Day.values()) {
            System.out.println(day + " -> " + dayType(day) + " (score=" + score(day) + ")");
        }
    }

    /** 多标签合并、箭头直接返回值。 */
    private String dayType(Day day) {
        return switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> "fun day";
            case TUESDAY -> "boring";
            case THURSDAY, SATURDAY -> "ok";
            case WEDNESDAY -> "hump day";
        };
    }

    /** 代码块中用 yield 返回值。 */
    private int score(Day day) {
        return switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> {
                int len = day.name().length();
                yield len * 2;
            }
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> day.name().length();
            case WEDNESDAY -> 9;
        };
    }
}
