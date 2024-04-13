// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.function.Function;

interface Program {

  static void main(String[] args) {
    System.out.println("Hello World!");

    final Function<String, Integer> fun = String::length;
    final var n = fun.apply("abcdef");
    System.out.println(n);
  }

}
