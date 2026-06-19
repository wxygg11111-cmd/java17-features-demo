package com.example.java17;

import com.example.java17.misc.MiscDemo;
import com.example.java17.patternmatching.PatternMatchingDemo;
import com.example.java17.records.RecordDemo;
import com.example.java17.sealed.SealedDemo;
import com.example.java17.streamapi.StreamApiDemo;
import com.example.java17.collections.CollectionsDemo;
import com.example.java17.localtypes.LocalTypeDemo;
import com.example.java17.switchexpr.SwitchExpressionDemo;
import com.example.java17.textblocks.TextBlockDemo;
import java.util.List;

/**
 * Java 17 特性演示入口。统一调度各 {@link Demo} 实现。
 * 预览特性演示（switch 模式匹配）未在此引入，需单独用 --enable-preview 运行，见 build.sh 的 preview 目标。
 */
public class Main {

    public static void main(String[] args) {
        List<Demo> demos = List.of(
                new RecordDemo(),
                new SealedDemo(),
                new PatternMatchingDemo(),
                new TextBlockDemo(),
                new SwitchExpressionDemo(),
                new StreamApiDemo(),
                new CollectionsDemo(),
                new LocalTypeDemo(),
                new MiscDemo());

        System.out.println("=".repeat(60));
        System.out.println(" Java 17 Features Demo  (JDK " + System.getProperty("java.version") + ")");
        System.out.println("=".repeat(60));

        for (Demo demo : demos) {
            System.out.println("\n--- " + demo.title() + " ---");
            try {
                demo.run();
            } catch (Exception e) {
                System.out.println("[demo failed] " + e);
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" All demos finished. Try the preview demo:");
        System.out.println("   ./build.sh preview");
        System.out.println("=".repeat(60));
    }
}
