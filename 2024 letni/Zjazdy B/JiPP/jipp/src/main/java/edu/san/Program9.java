// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface Program9 {

  static final int n = 3;

  static void foo(int n) {
    System.out.println("--2 " + n);
  }

  static void goo() {
    System.out.println("--3 " + n);
    final var n = 7;
    System.out.println("--4 " + n);
  }

  static void main(String... args) {
    System.out.println("--1 " + n);
    foo(5);
    goo();
  }
}
