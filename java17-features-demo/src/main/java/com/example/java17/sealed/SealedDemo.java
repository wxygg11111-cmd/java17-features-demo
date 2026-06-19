package com.example.java17.sealed;

import com.example.java17.Demo;

/**
 * 演示 Sealed Classes（JEP 409，Java 17 正式）。
 * 通过 {@code sealed ... permits ...} 限定可实现的子类，配合 instanceof 模式匹配可做到穷尽分支。
 */
public class SealedDemo implements Demo {

    public sealed interface Shape permits Circle, Rectangle, Triangle {
        double area();
    }

    /** record 隐式 final，天然适合作为密封类型的许可子类。 */
    public record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    public record Rectangle(double width, double height) implements Shape {
        @Override
        public double area() {
            return width * height;
        }
    }

    /** 非 record 子类需显式声明为 final 或 non-sealed。 */
    public static final class Triangle implements Shape {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        public double base() {
            return base;
        }

        public double height() {
            return height;
        }

        @Override
        public double area() {
            return 0.5 * base * height;
        }

        @Override
        public String toString() {
            return "Triangle[base=" + base + ", height=" + height + "]";
        }
    }

    @Override
    public String title() {
        return "Sealed Classes (JEP 409)";
    }

    @Override
    public void run() {
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4), new Triangle(6, 5) };
        double total = 0;
        for (Shape s : shapes) {
            System.out.println(s + " -> area = " + String.format("%.2f", s.area()));
            total += s.area();
        }
        System.out.println("total area = " + String.format("%.2f", total));

        // 密封类型 + instanceof 模式匹配：编译器知道全部子类，分支可覆盖穷尽
        System.out.println("describe(first) = " + describe(shapes[0]));
    }

    String describe(Shape shape) {
        if (shape instanceof Circle c) {
            return "a circle of radius " + c.radius();
        } else if (shape instanceof Rectangle r) {
            return "a rectangle " + r.width() + "x" + r.height();
        } else if (shape instanceof Triangle t) {
            return "a triangle with base " + t.base();
        }
        // 由于 Shape 是 sealed，此处对三个许可子类之外的形态不可达
        throw new IllegalStateException("unknown shape: " + shape);
    }
}
