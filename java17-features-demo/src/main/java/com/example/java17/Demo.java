package com.example.java17;

/**
 * 所有特性演示的统一接口。每个演示类实现 {@code title()} 与 {@code run()}，
 * 由 {@link Main} 统一调度，本身也展示了 Java 17 的接口与 List 用法。
 */
public interface Demo {
    String title();

    void run();
}
