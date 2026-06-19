package com.example.java17.random;

import com.example.java17.Demo;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;

/**
 * 演示 Enhanced Pseudo-Random Number Generators（JEP 356，Java 17 正式）。
 *
 * <p>引入统一的 {@link RandomGenerator} 接口与 {@link RandomGeneratorFactory} 工厂，
 * 内置多族新算法（LXM、Xoshiro 等），可按名称选取、按种子复现，替代直接 {@code new Random()} 的写法。
 */
public class RandomDemo implements Demo {

    /** 按名称创建带种子的生成器：同算法同种子产生完全相同的序列，便于测试与复现。 */
    static RandomGenerator named(String algorithm, long seed) {
        return RandomGeneratorFactory.of(algorithm).create(seed);
    }

    /** 取前 count 个 int，用于验证同种子可复现。 */
    static List<Integer> firstInts(String algorithm, long seed, int count) {
        return named(algorithm, seed).ints(count).boxed().collect(Collectors.toList());
    }

    /** 在 [origin, bound) 内生成 count 个值。 */
    static List<Integer> boundedInts(String algorithm, long seed, int count, int origin, int bound) {
        return named(algorithm, seed).ints(count, origin, bound).boxed().collect(Collectors.toList());
    }

    /** 列出 JDK 内置的全部算法名（已排序）。 */
    static List<String> algorithmNames() {
        return RandomGeneratorFactory.all()
                .map(RandomGeneratorFactory::name)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String title() {
        return "Enhanced PRNG (JEP 356)";
    }

    @Override
    public void run() {
        String algo = "L64X128MixRandom";
        long seed = 17L;

        System.out.println("algorithm = " + algo + ", seed = " + seed);
        System.out.println("first 5 ints = " + firstInts(algo, seed, 5));

        List<Integer> a = firstInts(algo, seed, 5);
        List<Integer> b = firstInts(algo, seed, 5);
        System.out.println("reproducible (a.equals(b)) = " + a.equals(b));

        System.out.println("bounded [10,20) = " + boundedInts(algo, seed, 8, 10, 20));

        List<String> names = algorithmNames();
        System.out.println("available algorithms (" + names.size() + ") = " + names);
        System.out.println("contains L64X128MixRandom = " + names.contains(algo));
    }
}
