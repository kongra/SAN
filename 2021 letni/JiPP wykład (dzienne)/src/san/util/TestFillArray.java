package san.util;

import org.jetbrains.annotations.NotNull;

public class TestFillArray {

  static int sumArrayOfInts(@NotNull FillArray<Integer> a) {
    int result = 0;
    for (int i = 0; i < a.length(); i++) {
      result += a.get(i);
      // result += (... : Integer)
      // auto-unboxing
      // result += ((Integer) a.get(i)).intValue();
    }
    return result;
  }

  static double sumArrayOfNumbers(@NotNull FillArray<Number> a) {
    double result = 0;
    for (int i = 0; i < a.length(); i++) {
      result += a.get(i).doubleValue();
    }
    return result;
  }

  public static void main(String... args) {
    FillArray<Integer> a1 = new FillArrayImpl<>(10);
    System.out.println(a1);

    int n = 4;
    a1.add(n); // <==> a1.add(Integer.valueOf(n)) - auto-boxing int=>Integer

    a1.add(1);
    a1.add(5).add(10);
    // a1.add("3.14");

    System.out.println(a1);
//    for (int i = 0; i < 100; i++) {
//      a1.add(i);
//    }

    System.out.println(sumArrayOfInts(a1));

    // System.out.println(sumArrayOfNumbers(a1)); BŁĄD
    // Ponieważ Integer <: Number, ALE jednocześnie
    // FillArray<Integer> NIE JEST <: FillArray<Number>
  }

}
