// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface Program3 {

  // POLIMORFIZM
  // * poli(s), morphos
  // * mnogość (wielość, wielorakość) form
  // * właściwość podprogramów - procedur, metod, funkcji
  // * polega na tym, że dana procedura polimorficzna może być wywoływana na
  // argumentach różnych typów.

  static double foo(double x) {
    return 2 * x + 3;
  }

  static double moo(double x) { // moo : double -> double
    return 2 * x + 3;
  }

  static long moo(long n) { // moo : long -> long
    return 2 * n + 4;
  }

  static void main(String... args) {
    foo(3.14159);
    foo(2L);

    moo(3.14);
    moo(2L);

  }

}
