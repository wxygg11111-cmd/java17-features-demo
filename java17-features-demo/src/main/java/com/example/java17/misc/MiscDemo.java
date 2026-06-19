package com.example.java17.misc;

import com.example.java17.Demo;
import java.util.HexFormat;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 演示 Java 17 周边的实用 API：
 * HexFormat（Java 17）、String 增强、Optional.isEmpty，以及 Helpful NullPointerException。
 */
public class MiscDemo implements Demo {

    @Override
    public String title() {
        return "Misc: HexFormat, String API & Helpful NPE";
    }

    @Override
    public void run() {
        // HexFormat（Java 17）：字节与十六进制字符串互转
        HexFormat hex = HexFormat.of();
        byte[] data = {0x01, (byte) 0xFF, 0x4A, 0x7e};
        String hexString = hex.formatHex(data);
        System.out.println("HexFormat.formatHex = " + hexString);
        byte[] decoded = hex.parseHex(hexString);
        System.out.println("HexFormat.parseHex back = "
                + IntStream.range(0, decoded.length)
                        .map(i -> decoded[i] & 0xFF)
                        .mapToObj(Integer::toHexString)
                        .toList());

        // String.repeat / indent / stripIndent / transform
        System.out.println("=".repeat(20));
        System.out.println("indent(4):\n" + "line1\nline2".indent(4));
        System.out.println("transform(strip) = [" + "  hello  ".transform(String::strip) + "]");
        System.out.println("stripIndent applied to text block alignment");

        // Optional.isEmpty（Java 11）
        Optional<String> empty = Optional.empty();
        System.out.println("Optional.isEmpty = " + empty.isEmpty());

        // Helpful NullPointerException（JEP 358）：指出哪个变量为 null
        try {
            String s = null;
            int len = s.length();
            System.out.println(len);
        } catch (NullPointerException npe) {
            System.out.println("Helpful NPE message: " + npe.getMessage());
        }
    }
}
