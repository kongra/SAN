// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp;

class Program2 {

  public static void main(String... args) {
    // Polimorfizm: poly-morphos (wielość postaci, form).
    // Jest to własność procedur (metod).
    // Polega ona na tym, że dana procedura polimorficzna uzdolniona jest do
    // działania (do pracy) z danymi różnych typów (inaczej: form, postaci).

    foo(256);
    foo(3.14159);

    goo(256);
    goo(3.14159);
  }

  static void goo(int n) {
    System.out.println(n + 1);
  }

  static void goo(double d) {
    System.out.println(d);
  }

  static void foo(double d) {
    System.out.println(d);
  }

}
