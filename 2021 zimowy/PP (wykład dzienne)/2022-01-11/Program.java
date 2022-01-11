class Program {

  static Component o1 = new Component(4);

  public static void main(String[] args) {
    o1.m1(5);
    o1.m1(7);

    // o1.m2(5);
    // BŁĄD: nie ma metody m2 zdefiniowanej w
    // klasie Component, a to oznacza, że '
    // obiekty tej klasy NIE SĄ uzdolnione
    // do obsługi tego rodzaju komunikatów.

    o1.printYourself();

    System.out.println(o1.getX1());

    Counter c1 = new Counter(0);
    c1.inc();
    c1.inc();
    c1.inc();

    System.out.println(c1.value());

    Counter c2 = new Counter(100);
    System.out.println(c2.inc().inc().inc().value());

  }

}
