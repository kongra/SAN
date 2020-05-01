package san.jipp.funprog.counter;

public class TestCounter {

  public static void main(String... args) {
    long result = sumCounter();

    for (int i = 0; i < 10; i++) {
      System.out.println(result);
    }
  }

  public static long sumCounter() {
    Counter c1 = Counter.make(0);
    long result = 0;
    for (int i = 0; i < 1000000000; i++) {
      result += c1.next() % 2;
    }
    return result;
  }

}
