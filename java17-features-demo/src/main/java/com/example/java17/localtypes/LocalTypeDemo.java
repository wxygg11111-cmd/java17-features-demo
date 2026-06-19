package com.example.java17.localtypes;

import com.example.java17.Demo;
import java.util.List;

/**
 * 演示 Local 静态类型声明（JEP 395，Java 16 正式）：
 * record、enum、interface 可声明在方法内部，作为局部的辅助类型，作用域仅限该方法。
 */
public class LocalTypeDemo implements Demo {

    /** 用局部 record 表示点对，求两点间距离与中点。 */
    public static double distance(int x1, int y1, int x2, int y2) {
        record Point(int x, int y) {
            double distanceTo(Point other) {
                int dx = x - other.x;
                int dy = y - other.y;
                return Math.sqrt(dx * dx + dy * dy);
            }
        }
        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);
        return a.distanceTo(b);
    }

    /** 用局部 enum 把状态码映射为文案，避免污染包级命名空间。 */
    static String describeStatus(int code) {
        enum Status { OK(200, "OK"), NOT_FOUND(404, "Not Found"), ERROR(500, "Server Error");
            final int code; final String text;
            Status(int code, String text) { this.code = code; this.text = text; }
            static Status of(int code) {
                for (Status s : values()) if (s.code == code) return s;
                throw new IllegalArgumentException("unknown status: " + code);
            }
        }
        return Status.of(code).text;
    }

    @Override
    public String title() {
        return "Local Type Declarations (JEP 395)";
    }

    @Override
    public void run() {
        System.out.println("distance (3,4)->(6,8) = " + distance(3, 4, 6, 8));
        for (int code : List.of(200, 404, 500)) {
            System.out.println("status " + code + " -> " + describeStatus(code));
        }
    }
}
