// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface PolimorfizmAdHoc {

  // POLIMORFIZM
  // * poli, morphos
  // * WIELOŚĆ (MNOGOŚĆ) FORM
  // * W programowaniu (w językach programowania) jest to cecha podprogramu
  //   (procedury, metody - również statycznej).
  // * Polega ona na tym, że dany podprogram (procedura, metoda) może pracować (używać) danych o różnych typach.

  static double foo(double x) {
    return 2 * x + 3;
  }

  static double moo(double x) {
    return x + 5;
  }

  static long moo(long n) {
    return n - 5;
  }

  static void main(String... args) {
    int n = 5;
    double x = n;

    var y1 = foo(5); // 5 jest typu int
    var y2 = foo(3.14159); // 3.14159 jest typu double
    // WNIOSEK 1 Z POWYŻSZEGO: foo w pewnym sensie jest polimorficzna.
    // foo(5) to w istocie rzeczy foo(5.0) - mamy tu do czynienia z niejawną konwersją int -> double.
    // WNIOSEK 2: Polimorficzna natura procedury foo jest 'udawana', z powodu konwersji int -> double.
    // Polimorfizm o takiej postaci nie jest prawdziwym polimorfizmem. Nazywamy go polimorfizmem AD-HOC.

    System.out.println(y1 + "," + y2);

    var y3 = moo(5.67);
    var y4 = moo(7L);
    // Przeciążanie nazw podprogramów (procedur, metod) jest również odmianą polimorfizmu AD-HOC.
  }

}
