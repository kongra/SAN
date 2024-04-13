// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface Program1 {

  static void main(String[] args) {
    final byte b1 = 34; // 0010 0010
    final short s1 = b1; // 0000 0000 0010 0010 Niejawna konwersja wartości typu
                         // byte na wartość typu short
    System.out.println(s1);

    final short s2 = 56;
    // byte b2 = s2; // błąd kompilacji
    final var b2 = (byte) s2; // Jawna konwersja (rzutowanie)
    System.out.println(b2);

    final short s3 = 129;
    final var b3 = (byte) s3;
    System.out.println(b3);

    final var n1 = Long.MAX_VALUE - 1;
    final double d1 = n1;
    final var n2 = (long) d1;
    System.out.println(n1);
    System.out.println(n2);
    System.out.println(n1 == n2);

    final var diff1 = 1.1 - 0.9;
    System.out.println(diff1);

    final var c1 = 'a';
    System.out.println(c1);
    System.out.println((int) c1);

    final char c2 = (char) 2 ^ 16;
    System.out.println(c2);

  }

}
