// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp;

class Program1 {

  public static void main(String... args) {
    final byte b = 35;
    final short s = b;

    final var l1 = Long.MIN_VALUE / 2;
    float f1 = l1;
    final var l2 = (long) f1;

    System.out.println(b);
    System.out.println(s);

    System.out.println(l1);
    System.out.println(f1);
    System.out.println(l2);

    System.out.println(l1 == l2);

    final var x = 100_000_000L;
    f1 = x;
    final var f2 = (float) x + 1;
    System.out.println(f1 == f2);

    final var x1 = 0.0;
    System.out.println(x1 / x1);

    final var nan = Double.NaN;
    System.out.println(nan == nan);

  }

}
