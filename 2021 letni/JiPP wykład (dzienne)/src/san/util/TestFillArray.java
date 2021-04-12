package san.util;

public class TestFillArray {

  public static void main(String... args) {
    FillArray a1 = new FillArrayImpl(10);
    System.out.println(a1);

    a1.add(4).add(5).add(10);
    System.out.println(a1);

    for (int i = 0; i < 100; i++) {
      a1.add(i);
    }

    System.out.println(a1);
  }

}
