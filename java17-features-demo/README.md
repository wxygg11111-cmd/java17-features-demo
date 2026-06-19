# Java 17 Features Demo

[![CI](https://github.com/wxygg11111-cmd/java17-features-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/wxygg11111-cmd/java17-features-demo/actions/workflows/ci.yml)


一个纯 Java 17 工程，集中演示 Java 9–17 期间引入、并在 Java 17（LTS）中可用的语言与 API 特性。零第三方依赖，仅用 JDK 标准库。

## 演示内容

| 模块 | 特性 | JEP / 版本 | 是否预览 |
|------|------|-----------|---------|
| `records` | Record Classes | JEP 395（Java 16 正式） | 否 |
| `sealed` | Sealed Classes | JEP 409（Java 17 正式） | 否 |
| `patternmatching` | Pattern Matching for instanceof | JEP 394（Java 16 正式） | 否 |
| `textblocks` | Text Blocks | JEP 378（Java 15 正式） | 否 |
| `switchexpr` | Switch Expressions | JEP 361（Java 14 正式） | 否 |
| `streamapi` | `Stream.toList()` / `mapMulti` | Java 16 | 否 |
| `collections` | 不可变集合工厂、`Collectors.teeing` | Java 9-12 | 否 |
| `localtypes` | 局部类型声明（record/enum） | JEP 395（Java 16） | 否 |
| `misc` | HexFormat、String 增强、Helpful NPE | Java 11–17 | 否 |
| `varfeature` | 局部变量类型推断 `var` | JEP 286（Java 10 正式） | 否 |
| `random` | Enhanced PRNG（`RandomGenerator`/`Factory`） | JEP 356（Java 17 正式） | 否 |
| `serialization` | 上下文反序列化过滤器 | JEP 415（Java 17 正式） | 否 |
| `preview` | Pattern Matching for switch | JEP 406（Java 17 预览） | **是** |

每个演示实现公共接口 `Demo`（`title()` + `run()`），由 `Main` 统一调度。

## 目录结构

```
java17-features-demo/
├── build.sh                # 基于 javac 的构建/运行脚本
├── pom.xml                 # 可选：Maven 构建（需自行安装 Maven）
├── README.md
└── src/main/java/com/example/java17/
    ├── Main.java           # 主入口（稳定特性）
    ├── Demo.java           # 演示公共接口
    ├── records/RecordDemo.java
    ├── sealed/SealedDemo.java
    ├── patternmatching/PatternMatchingDemo.java
    ├── textblocks/TextBlockDemo.java
    ├── switchexpr/SwitchExpressionDemo.java
    ├── streamapi/StreamApiDemo.java
    ├── misc/MiscDemo.java
    ├── varfeature/VarDemo.java
    ├── random/RandomDemo.java
    ├── serialization/SerializationDemo.java
    └── preview/SwitchPatternPreview.java   # 需 --enable-preview
```

## 环境要求

- JDK 17（任意发行版，如 OpenJDK 17）

```bash
java -version
```

## 使用方式

### 方式零：Maven Wrapper（无需预装 Maven）

```bash
cd java17-features-demo
./mvnw compile             # 编译稳定特性
./mvnw exec:java           # 运行主程序
./mvnw -Ppreview compile   # 编译预览特性
```

首次运行 `./mvnw` 会自动下载 Maven 3.9.9 到本地缓存。

### 方式一：build.sh（无需 Maven）

```bash
cd java17-features-demo
./build.sh run        # 编译并运行稳定特性主程序
./build.sh build      # 仅编译
./build.sh preview    # 编译并运行预览特性演示（switch 模式匹配）
./build.sh clean      # 清理 out/
```

### 方式二：Maven（可选）

```bash
mvn compile                  # 编译稳定特性（自动排除 preview 目录）
mvn exec:java                # 运行主程序 Main
mvn -Ppreview compile        # 编译预览特性（--enable-preview，仅编译验证）
```

> 预览特性的 *运行* 仍推荐 `./build.sh preview`：`exec:java` 在 Maven 自身 JVM 内执行，
> 无法对其开启 `--enable-preview`，故不用于运行 preview 类。

### 方式三：手动 javac/java

```bash
cd java17-features-demo
mkdir -p out
find src/main/java -name '*.java' ! -path '*/preview/*' \
  -print0 | xargs -0 javac --release 17 -d out
java -cp out com.example.java17.Main

# 预览特性
find src/main/java -name '*.java' -print0 \
  | xargs -0 javac --enable-preview --release 17 -d out
java --enable-preview -cp out com.example.java17.preview.SwitchPatternPreview
```

## 测试

工程含 JUnit 5 单元测试，覆盖各演示的核心逻辑（record 校验与方法、密封类型面积、
instanceof 分类、switch 表达式、Stream 展开、HexFormat 互转、PRNG 复现与边界、反序列化过滤器、var 推断等）。

```bash
cd java17-features-demo
./mvnw test                         # 运行全部稳定特性测试
./mvnw -Ppreview test               # 运行含预览特性的全部测试（--enable-preview）
./mvnw -Dtest=RecordDemoTest test   # 运行单个测试类
```

CI（`.github/workflows/ci.yml`）会在每次 push/PR 时自动运行 `./mvnw test` 与 `./mvnw -Ppreview test`（后者编译并测试预览特性）。

## 关于预览特性

`preview/SwitchPatternPreview.java` 使用了 Java 17 的预览特性 *Pattern Matching for switch*（JEP 406）。该特性在 Java 17 为预览，编译与运行都必须加 `--enable-preview`，故单独提供 `build.sh preview` 目标，未并入主程序。它在 Java 21 转为正式特性。
