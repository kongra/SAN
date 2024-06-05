// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import telsos.DynVar;

class Program10 {

  static final DynVar<Integer> n = DynVar.newInstance();

  static int m = 3;

  static void foo() {
    System.out.println("foo::" + m);
  }

  static void goo() {
    System.out.println("goo::" + n.get());
  }

  static void main(String... args) {
    // test1();

    goo();
    n.exec(4, () -> {
      goo();
      n.exec(5, () -> {
        goo();
        n.exec(6, () -> {
          goo();
        });
        goo();
      });
    });
    goo();

  }

  private static void test1() {
    // Tutaj m jest 3
    foo();

    final var tmp = m;
    m = 4;
    // Tutaj m jest 4
    try {
      foo();
    } finally {
      m = tmp;
    }

    // Tutaj m jest z powrotem 3
    foo();
  }

}
