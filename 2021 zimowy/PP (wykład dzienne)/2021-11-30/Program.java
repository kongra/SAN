public class Program {

  // KLASA pojęciowo jest tym samym, co TYP danych.
  // W Javie (również w C++, C#) ma ona dodatkowo pewne właściwości, różniące ją od
  // tzw. typów prostych (primitive-types):
  // - Obiekty, czyli wartości/instancje KLASY mają zwykle nietrywialną i nieatomową strukturę
  //   wewnętrzną.
  // - Obiekty mogą mieć ze sobą stowarzyszone operacje (zachowania) będące odpowiedziami
  //   na bodźce dochodzące z zewnątrz. Te operacje nazywamy METODAMI. Są one zwykle
  //   definiowane wewnątrz definicji klasy.

  // Zadanie. Opracować reprezentację punktów w przestrzeni 2D.
  // - Jakie działania mamy zrealizować w ramach tej funkcjonalności?
  // - Jaka będzie reprezentacja punktów?
  //
  // p1 = (1, 2);
  // p2 = (3, 4);
  // a. odległość pomiędzy punktami
  // b. punkty można potraktować jak wektory z przestrzeni R2 i wówczas ma sens ich dodawanie
  //    p1 + p2 = (1+3, 2+4);

  static double[] makePoint(double x, double y) {
    return new double[] { x, y };
  }

  static double[] addPoints(double[] p1, double[] p2) {
    return makePoint(p1[0] + p2[0], p1[1] + p2[1]);
  }

  static String asString(double[] p) {
    return "(" + p[0] + ", " + p[1] + ")";
  }

  public static void main(String[] args) {
    var p1 = makePoint(1, 2);
    var p2 = makePoint(3, 4);
    var p3 = addPoints(p1, p2);

    var p4 = new double[] {1, 2, 3, 4, 5};
    var p5 = addPoints(p4, p3);

    var p6 = new double[] {6};
    var p7 = addPoints(p5, p6);

    System.out.println(asString(p1));
    System.out.println(asString(p2));
    System.out.println(asString(p3));
    System.out.println(asString(p5));
    System.out.println(asString(p7));
  }

}
