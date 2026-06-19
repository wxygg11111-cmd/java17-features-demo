package com.example.java17.preview;

import com.example.java17.Demo;

/**
 * 预览特性：Pattern Matching for switch（JEP 406，Java 17 预览）。
 *
 * <p>编译与运行均需 {@code --enable-preview}：
 * <pre>
 *   javac --enable-preview --release 17 ...
 *   java  --enable-preview -cp ...
 * </pre>
 * 本工程通过 {@code ./build.sh preview} 一键编译运行。
 *
 * <p>注意：Java 17 预览版守卫使用 {@code &&}，而非 Java 18+ 才引入的 {@code when}。
 */
public class SwitchPatternPreview implements Demo {

    sealed interface Shape permits Circle, Square, Triangle {
    }

    record Circle(double radius) implements Shape {
    }

    record Square(double side) implements Shape {
    }

    record Triangle(double base, double height) implements Shape {
    }

    public static void main(String[] args) {
        new SwitchPatternPreview().run();
    }

    @Override
    public String title() {
        return "[Preview] Pattern Matching for switch (JEP 406)";
    }

    @Override
    public void run() {
        System.out.println("--- " + title() + " ---");
        Object[] values = { 42, "hi", 3.14, new Circle(2), new Square(3), new Triangle(4, 5) };
        for (Object v : values) {
            System.out.println(formatValue(v));
        }
    }

    /** switch 直接对类型/守卫进行模式匹配，无需显式强转；密封类型支持穷尽性检查。 */
    private String formatValue(Object o) {
        return switch (o) {
            case Integer i && i > 0 -> "positive int " + i;
            case Integer i -> "non-positive int " + i;
            case String s -> "string \"" + s + "\"";
            case Circle c -> "circle r=" + c.radius();
            case Square s -> "square side=" + s.side();
            case Triangle t -> "triangle area=" + (0.5 * t.base() * t.height());
            case null -> "null";
            default -> "something else: " + o;
        };
    }
}
