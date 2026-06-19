package com.example.java17.streamapi;

import com.example.java17.Demo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 演示 Java 16+ 的 Stream 增强：{@code Stream.toList()} 与 {@code mapMulti}。
 */
public class StreamApiDemo implements Demo {

    /** 把整数 n 展开为 n 个元素（n*10+0 .. n*10+n-1），供 mapMulti 与测试复用。 */
    static List<Integer> expand(int n) {
        List<Integer> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(n * 10 + i);
        }
        return result;
    }

    @Override
    public String title() {
        return "Stream API: toList() & mapMulti (Java 16+)";
    }

    @Override
    public void run() {
        // Stream.toList() 收集为不可修改 List
        List<String> upper = Stream.of("java", "kotlin", "scala")
                .map(String::toUpperCase)
                .toList();
        System.out.println("toList(): " + upper);

        // mapMulti：一对多展开，比 flatMap 更直接地在回调里 push 元素
        List<Integer> expanded = Stream.of(1, 2, 3)
                .mapMulti((Integer n, Consumer<Integer> downstream) -> expand(n).forEach(downstream))
                .toList();
        System.out.println("mapMulti(): " + expanded);

        // 行为差异：Collectors.toList() 返回可变 List，Stream.toList() 不可变
        var mutable = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println("Collectors.toList() class = " + mutable.getClass().getSimpleName());
        try {
            upper.add("X");
        } catch (UnsupportedOperationException e) {
            System.out.println("Stream.toList() is unmodifiable (add throws UnsupportedOperationException)");
        }
    }
}
