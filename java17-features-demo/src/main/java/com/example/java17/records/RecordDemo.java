package com.example.java17.records;

import com.example.java17.Demo;

/**
 * 演示 Record Classes（JEP 395，Java 16 正式，Java 14 预览）。
 */
public class RecordDemo implements Demo {

    /** 基本记录：自动生成访问器、equals、hashCode、toString。 */
    public record Point(int x, int y) {
        // 紧凑构造器：做参数校验
        public Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("coordinates must be non-negative");
            }
        }

        public double distanceTo(Point other) {
            int dx = x - other.x;
            int dy = y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        public Point translate(int dx, int dy) {
            return new Point(x + dx, y + dy);
        }
    }

    /** 记录可实现接口，并可自定义方法。 */
    public record Range(int start, int end) implements Comparable<Range> {
        public Range {
            if (start > end) {
                throw new IllegalArgumentException("start must be <= end");
            }
        }

        public int length() {
            return end - start;
        }

        @Override
        public int compareTo(Range other) {
            int c = Integer.compare(start, other.start);
            return c != 0 ? c : Integer.compare(end, other.end);
        }
    }

    @Override
    public String title() {
        return "Record Classes (JEP 395)";
    }

    @Override
    public void run() {
        Point p1 = new Point(3, 4);
        Point p2 = p1.translate(6, 8);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p1.x() = " + p1.x() + ", p1.y() = " + p1.y());
        System.out.println("distance p1 -> p2 = " + p1.distanceTo(p2));
        System.out.println("p1.equals(new Point(3,4)) = " + p1.equals(new Point(3, 4)));
        System.out.println("p1.hashCode() = " + p1.hashCode());

        Range r = new Range(2, 10);
        System.out.println("range = " + r + ", length = " + r.length());

        try {
            new Point(-1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("compact constructor validation: " + e.getMessage());
        }
    }
}
