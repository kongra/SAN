class Program {

  public static void main(String[] args) {
    int n = 1; // 32b = 4B
    System.out.println(n);

    n = 3;
    System.out.println(n);

    System.out.println(Integer.MIN_VALUE);
    System.out.println(Integer.MAX_VALUE);

    byte  b = 0; //  8b = 1B, -128 .. 0 .. 127
    short c = b; // 16b = 2B, -??  .. 0 .. ??
    int   i = c; // 32b = 4B, ... (D)
    long  l = i; // 64b = 8B, ...

    //    A <: B - A jest PODTYPEM B (A jest PODZBIOREM B)
    // Jeżeli A <: B oraz x należy do A, to należy on również do B
    // A < B
    // Ada: N : Positive := 2;

    float  x = 0.25f; // 32b
    double d = x;     // 64b (D)

    // 1/3 = 0.3333333333...

    x = l;
    char c1 = 'ą'; // 16b

    // byte <: short <: int <: long <: float <: double
    //         char  <: int <: long <: float <: double

    boolean b1 = true;
    b1 = false;

    // PREDYKAT - wyrażenie mające wartość prawda|fałsz (true|false)
    b1 = c1 == 'c';
    // OPERATORY RELACYJNE: ==, !=, <, <=, >, >= (.equals), zwracają true|false

    // FORMY SKŁADNIOWE W JAVIE
    // 1. WYRAŻENIE (ang. EXPRESSION) - forma posiadająca wartość
    // np. c1 == '4' ma wartość false, ponieważ c1 == 'ą' (zapisujemy to ... => false)
    // np. i + 4 => 4, ponieważ i == 0
    //
    // 2. INSTRUKCJA -  formy nie posiadające wartości, wykorzystywane do wykonywania
    // (ang. STATEMENT) działań posiadających efekty uboczne. Takim efektem może być np.
    //                  wypisanie tekstu na ekranie lub zmiana wartości zmiennej.

    // INSTRUKCJA WARUNKOWA if
    // if (<predicate>) {
    //   <consequent>
    // } else {
    //   <alternative>
    // }
    //
    // consequent - następnik
    // alternative - alternatywa

    if (c1 == 'c') {
      System.out.println("Warunek spełniony");
    }

    // if (c1 == 'a') {
    //   ...
    // } else if (c1 == 'b') {
    //   ...
    // } else if (c1 == 'c') {
    //   ...
    // }

    if (c1 == 'c') {
      System.out.println("Warunek spełniony");
    } else {
      System.out.println("Warunek spełniony nie jest");
    }

    // INSTRUKCJA WARUNKOWA if NIE POSIADA WARTOŚCI, PONIEWAŻ JEST INSTRUKCJĄ
    // np.
    // int n1 = if (c1 == 'a') {
    //   4
    // } else {
    //   3
    // }

    // ISTNIEJE NATOMIAST WYRAŻENIOWY ODPOWIEDNIK INSTRUKCJI if. JEST TO TZW.
    // ternary-conditional (trójelementowe wyrażenie warunkowe)
    // <predicate> ? <consequent> : <alternative>
    int n1 = c1 == 'a' ? 4 : 3;

  }

}
