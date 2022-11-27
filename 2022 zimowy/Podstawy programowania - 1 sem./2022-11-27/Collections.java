import java.util.*;

class Collections {

  static long fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n-1) + fib(n-2);
  }

  static ArrayList<Long> fibSeries(int n) {
    var series = new ArrayList<Long>(n+1);
    for (int i = 0; i <= n; i++) {
      var value = fib(i);
      series.add(value);
    }

    return series;
  }

  public static void main(String[] args) {
    var series = fibSeries(20);
    System.out.println(series);
  }

  // Zadanie dla chętnych, na wcześniejsze zaliczenie:
  static long mystery(long i, long j, long k) {
    if (i == 0)           return k + 1;
    if (i == 1 && k == 0) return j;
    if (i == 2 && k == 0) return 0;
    if (k == 0)           return 1;

    return mystery(i-1, j, mystery(i, j, k-1));
  }
  // Oblicz mystery(4,4,4)
}
