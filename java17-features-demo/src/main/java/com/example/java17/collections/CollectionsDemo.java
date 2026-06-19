package com.example.java17.collections;

import com.example.java17.Demo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 演示 Java 9–12 引入、Java 17 可用的不可变集合工厂与收集器：
 * List/Set/Map.of、ofEntries、copyOf（Java 9），Collectors.toUnmodifiableList/Map（Java 10），
 * 以及 Collectors.teeing（Java 12）——一次遍历并行聚合多个统计量。
 */
public class CollectionsDemo implements Demo {

    /** 不可变 List/Set 工厂。 */
    static List<String> immutableList() {
        return List.of("java", "kotlin", "scala");
    }

    static Set<Integer> immutableSet() {
        return Set.of(1, 2, 3);
    }

    /** Map.of 最多 10 对；更多用 ofEntries。 */
    static Map<String, Integer> immutableMap() {
        return Map.of("one", 1, "two", 2, "three", 3);
    }

    /** Collectors.teeing：把同一流喂给两个下游收集器，再合并结果。这里求整数流的平均值。 */
    static double average(List<Integer> numbers) {
        return numbers.stream().collect(Collectors.teeing(
                Collectors.summingInt(Integer::intValue),
                Collectors.counting(),
                (sum, count) -> count == 0 ? 0.0 : (double) sum / count));
    }

    /** toUnmodifiableMap：收集为不可修改 Map。 */
    static Map<String, Integer> lengthMap(List<String> words) {
        return words.stream().collect(
                Collectors.toUnmodifiableMap(s -> s, String::length, (a, b) -> a));
    }

    @Override
    public String title() {
        return "Immutable Collections & teeing (Java 9-12)";
    }

    @Override
    public void run() {
        List<String> list = immutableList();
        System.out.println("List.of = " + list);
        try {
            list.add("x");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of is unmodifiable");
        }

        System.out.println("Set.of = " + immutableSet());
        System.out.println("Map.of = " + immutableMap());

        // copyOf：从可变集合派生不可变副本
        List<String> copy = List.copyOf(new java.util.ArrayList<>(list));
        System.out.println("List.copyOf = " + copy);

        List<Integer> nums = List.of(10, 20, 30);
        System.out.println("average of " + nums + " = " + average(nums));

        Map<String, Integer> lm = lengthMap(List.of("java", "kotlin", "scala"));
        System.out.println("toUnmodifiableMap(length) = " + lm);
    }
}
