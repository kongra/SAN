package san.lext03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import san.coll.fn.NoArg;

public class TestCollections {

  public static void main(String[] args) {
    // testSums();

    List<Integer> src1 = Arrays.asList(1, 2, 3);
    List<Double> src2 = Arrays.asList(1.0, 2.0, 3.0);
    List<String> src3 = Arrays.asList("aaa", "bbb");

    List<Object> dst = new ArrayList<>();
    copy(src1, dst, new NoArg<Integer>() {
      @Override
      public Integer call() {
        return 5;
      }

    });

    copy(src2, dst, new NoArg<Double>() {
      @Override
      public Double call() {
        return 3.14;
      }
    });

    copy(src3, dst, new NoArg<String>() {
      @Override
      public String call() {
        return "hehe";
      }
    });

  }

  public static <T> void copy(List<? extends T> source,
      List<? super T> destination, NoArg<T> creator) {

    for (T element : source) {
      destination.add(element);
    }

    T obj = creator.call();
  }

  private static void testSums() {
    long start = System.currentTimeMillis();

    List<Integer> ints = range2(100_000);
    long sum = 0;

    for (Iterator<Integer> it = ints.iterator(); it.hasNext();) {
      int i = it.next();
      sum += i;

      it.remove();
    }

    for (Integer i : ints) {
      sum += i;
    }

    System.out.println("wynik " + sum + " wyznaczony w "
        + (System.currentTimeMillis() - start) + " msecs");
  }

  private static List<Integer> range1(int n) {
    List<Integer> result = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      result.add(i);
    }
    return result;
  }

  private static List<Integer> range2(int n) {
    List<Integer> result = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      result.add(i);
    }
    return result;
  }

}
