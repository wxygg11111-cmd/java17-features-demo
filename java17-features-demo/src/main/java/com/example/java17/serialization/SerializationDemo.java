package com.example.java17.serialization;

import com.example.java17.Demo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 演示 Context-Specific Deserialization Filters（JEP 415，Java 17 正式）。
 *
 * <p>用 {@link ObjectInputFilter} 的过滤器构建器（allowFilter / rejectUndecidedClass）
 * 组合出「白名单」过滤器，按类限定可反序列化的类型，降低不可信数据反序列化的风险。
 */
public class SerializationDemo implements Demo {

    /** record 需显式声明 implements Serializable 才可被序列化。 */
    public record Person(String name, int age) implements Serializable {
    }

    /** 序列化对象为字节数组。 */
    static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }

    /** 在指定过滤器下反序列化。 */
    @SuppressWarnings("unchecked")
    static <T> T deserialize(byte[] bytes, ObjectInputFilter filter)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            ois.setObjectInputFilter(filter);
            return (T) ois.readObject();
        }
    }

    /**
     * JEP 415 构建器 API：仅允许给定包前缀的类（ALLOWED），
     * 其余类保持 UNDECIDED 再由 rejectUndecidedClass 收紧为 REJECTED；
     * 深度/引用等非类信息不受限（保持 UNDECIDED 即不额外约束）。
     */
    static ObjectInputFilter allowPackages(String... prefixes) {
        ObjectInputFilter allow = ObjectInputFilter.allowFilter(
                clazz -> clazz != null && Arrays.stream(prefixes)
                        .anyMatch(prefix -> clazz.getName().startsWith(prefix)),
                ObjectInputFilter.Status.UNDECIDED);
        return ObjectInputFilter.rejectUndecidedClass(allow);
    }

    @Override
    public String title() {
        return "Deserialization Filters (JEP 415)";
    }

    @Override
    public void run() {
        try {
            ObjectInputFilter filter = allowPackages(
                    "com.example.java17.serialization.", "java.lang.");

            Person p = new Person("Alice", 30);
            byte[] data = serialize(p);
            Person back = deserialize(data, filter);
            System.out.println("round-trip person = " + back);

            byte[] listData = serialize(new ArrayList<>(List.of("x")));
            try {
                deserialize(listData, filter);
                System.out.println("ArrayList unexpectedly deserialized");
            } catch (InvalidClassException e) {
                System.out.println("rejected ArrayList: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("[demo failed] " + e);
        }
    }
}
