class Program {

  static double bmi(double massKg, double heightMeters) {
    return massKg / (heightMeters * heightMeters);
  }

  static double y = 1.0;

  static double enemy(double x) {
    y = y + 1.0;
    return x + y;
  }

  public static void main(String[] args) {
    System.out.println("enemy => " + enemy(1.0));
    System.out.println("enemy => " + enemy(1.0));
    System.out.println("enemy => " + enemy(1.0));
    System.out.println("enemy => " + enemy(1.0));
  }

  // public static void main(String[] args) {
  //   System.out.println("Moje BMI wynosi " + bmi(80.0, 1.80));
  //   System.out.println("Moje BMI wynosi " + bmi(80.0, 1.80));
  //   System.out.println("Moje BMI wynosi " + bmi(80.0, 1.80));
  //   System.out.println("Moje BMI wynosi " + bmi(80.0, 1.80));
  //   System.out.println("Moje BMI wynosi " + bmi(80.0, 1.80));
  // }

}

// OBIEKT: Byt posiadający tożsamość

// TYP (DANYCH) w-g D. Scotta i Ch. Strachey'a
// 1. Zbiór więzów i ograniczeń nakładanych na
//    dane (obiekty) w celu zapewnienia poprawności
//    znaczeniowej wyrażeń budowanych z użyciem
//    tych danych (obiektów).
// 2. Zbiór danych (obiektów) spełniających ten
//    sam zbiór więzów i ograniczeń.
//
// KLASA = TYP
