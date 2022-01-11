public class Component {

  private double x1;
  // private oznacza, że dany składnik klasy
  // (pole, metoda lub konstruktor) jest widoczny
  // wyłącznie wewnątrz tej klasy. W ten sposób
  // wnętrze obiektu takiej klasy jest chronione
  // przed dostępem z zewnątrz

  // public oznacza pełną widoczność składnika klasy
  // wewnątrz i poza nią

  public Component(double x1) {
    this.x1 = x1;
  }

  // m1 jest METODĄ
  // * a więc mechanizmem reagującym na przyjście
  //   komunikatu o nazwie "m1"
  // * lub nazwą symboliczną rodzaju komunikatu, na
  //   którego przetworzenie obiekty klasy Component
  //   są przygotowane
  public void m1(double d) {
    this.x1 = d;
  }

  public double getX1() {
    return this.x1;
  }

  public void printYourself() {
    System.out.println("obiekt klasy Component ma x1 "
                       + this.x1);
  }
}
