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
| `misc` | HexFormat、String 增强、Helpful NPE | Java 11–17 | 否 |
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
    └── preview/SwitchPatternPreview.java   # 需 --enable-preview
```

## 环境要求

- JDK 17（任意发行版，如 OpenJDK 17）

```bash
java -version
```

## 使用方式

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

## 关于预览特性

`preview/SwitchPatternPreview.java` 使用了 Java 17 的预览特性 *Pattern Matching for switch*（JEP 406）。该特性在 Java 17 为预览，编译与运行都必须加 `--enable-preview`，故单独提供 `build.sh preview` 目标，未并入主程序。它在 Java 21 转为正式特性。
