package com.example.java17.textblocks;

import com.example.java17.Demo;

/**
 * 演示 Text Blocks（JEP 378，Java 15 正式）。
 * 用三引号书写多行字符串，自动管理缩进与换行。
 */
public class TextBlockDemo implements Demo {

    @Override
    public String title() {
        return "Text Blocks (JEP 378)";
    }

    @Override
    public void run() {
        String json = """
                {
                  "name": "Java 17",
                  "features": ["records", "sealed", "pattern matching"],
                  "lts": true
                }
                """;
        System.out.println("JSON block:");
        System.out.println(json);

        String sql = """
                SELECT id, name, email
                FROM   users
                WHERE  active = true
                ORDER  BY name
                """;
        System.out.println("SQL block:");
        System.out.println(sql);

        // formatted() 与文本块配合做模板
        String template = """
                Hello %s,
                Your account balance is %.2f.
                """.formatted("Bob", 1234.5);
        System.out.println("Formatted:");
        System.out.println(template);

        // \s 保留尾部空格；行尾反斜杠续行（取消换行）
        String colors = """
                red    \s
                green  \s
                blue   \s
                """;
        System.out.println("Trailing spaces preserved with \\s:");
        System.out.println("[" + colors + "]");

        String oneLine = """
                This is a single \
                long line of text.""";
        System.out.println("Line continuation with trailing backslash:");
        System.out.println(oneLine);
    }
}
