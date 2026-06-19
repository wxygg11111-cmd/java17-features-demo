package com.example.java17.varfeature;

import com.example.java17.Demo;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示 Local Variable Type Inference（var，JEP 286，Java 10 正式）。
 *
 * <p>var 让编译器推断局部变量类型。对匿名类、diamond 操作符等场景，
 * 推断出的类型常比「显式左值类型」更具体，从而保留更多类型信息。
 */
public class VarDemo implements Demo {

    /** var + diamond：推断为具体类型 ArrayList<String>，而非 List<String>。 */
    static List<String> appendWithInferredConcrete() {
        var list = new ArrayList<String>();
        list.add("java");
        list.add("17");
        return list;
    }

    /** var 保留匿名类的具体类型，因而可访问其内部新增的成员；用显式类型 Object 则做不到。 */
    static String anonymousGreeting() {
        var greeter = new Object() {
            final String target = "Java 17";

            String greet() {
                return "Hello, " + target + "!";
            }
        };
        return greeter.greet();
    }

    /** var 用于泛型方法返回类型推断。 */
    static int firstDoubled(List<Integer> numbers) {
        var doubled = numbers.stream().map(n -> n * 2);
        return doubled.findFirst().orElse(-1);
    }

    /** var 用于增强 for 与基本类型推断。 */
    static int sumOf(int... numbers) {
        var sum = 0;
        for (var n : numbers) {
            sum += n;
        }
        return sum;
    }

    @Override
    public String title() {
        return "Local Variable Type Inference: var (JEP 286)";
    }

    @Override
    public void run() {
        System.out.println("appendWithInferredConcrete() class = "
                + appendWithInferredConcrete().getClass().getSimpleName());
        System.out.println("appendWithInferredConcrete() = " + appendWithInferredConcrete());
        System.out.println("anonymousGreeting() = " + anonymousGreeting());
        System.out.println("firstDoubled([5,10]) = " + firstDoubled(List.of(5, 10)));
        System.out.println("sumOf(1,2,3,4) = " + sumOf(1, 2, 3, 4));
    }
}
