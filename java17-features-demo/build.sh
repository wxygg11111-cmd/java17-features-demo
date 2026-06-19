#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC="$ROOT/src/main/java"
OUT="$ROOT/out"
MAIN_CLASS="com.example.java17.Main"
PREVIEW_CLASS="com.example.java17.preview.SwitchPatternPreview"

clean() {
  rm -rf "$OUT"
}

build() {
  clean
  mkdir -p "$OUT"
  echo ">> Compiling Java 17 sources (stable features)..."
  find "$SRC" -name '*.java' ! -path '*/preview/*' -print0 \
    | xargs -0 javac --release 17 -d "$OUT"
  echo ">> Build OK -> $OUT"
}

run() {
  build
  echo ">> Running $MAIN_CLASS"
  echo
  java -cp "$OUT" "$MAIN_CLASS"
}

preview() {
  clean
  mkdir -p "$OUT"
  echo ">> Compiling ALL sources (incl. preview) with --enable-preview..."
  find "$SRC" -name '*.java' -print0 \
    | xargs -0 javac --enable-preview --release 17 -d "$OUT"
  echo ">> Running $PREVIEW_CLASS with --enable-preview"
  echo
  java --enable-preview -cp "$OUT" "$PREVIEW_CLASS"
}

case "${1:-run}" in
  clean)   clean ;;
  build)   build ;;
  run)     run ;;
  preview) preview ;;
  *)
    echo "Usage: $0 {clean|build|run|preview}"
    exit 1
    ;;
esac
