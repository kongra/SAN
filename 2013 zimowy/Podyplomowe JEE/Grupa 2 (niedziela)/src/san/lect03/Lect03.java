package san.lect03;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import san.math.Point2D;
import san.math.Point3D;
import san.util.Collections;
import san.util.Queue;
import san.util.QueueImpl;
import san.util.Ref;
import san.util.Timeit;

public class Lect03 {

  public static void main(String[] args) {
    test9();
    
    BigDecimal x = BigDecimal.valueOf(1.1);
    BigDecimal y = BigDecimal.valueOf(0.2);
    
    BigDecimal z = x.divide(y, 5, RoundingMode.HALF_EVEN);
    System.out.println(z);
    
    System.out.println(x.subtract(y));
  }

  private static void test9() {
    List<Point3D> src =
        Arrays.asList(new Point3D(1, 2, 3), new Point3D(3, 4, 6));
    List<Point2D> dst = new ArrayList<>();
    
    //
    Collections.addAll(src, dst);

    // Ref<Point2D> p;
    
    double x = 1.1 - 0.2;
    System.out.println(x == 0.9);
  }

  private static void addAll(List<Point2D> punkty, Point2D... nowe) {
    for (Point2D p : nowe) {
      punkty.add(p);
    }
  }

  private static void test8() {
    Role rola = Role.GUEST;
    System.out.println(rola);

    EnumSet<Role> role = EnumSet.of(Role.GUEST, Role.USER);
    role.add(Role.SUPERUSER);
  }

  private static void test7() {
    Map<String, String> osoby = new HashMap<String, String>();
    osoby.put("Jan", "Kowalski");
    osoby.put("Marek", "Nowak");

    System.out.println(osoby);

    for (Map.Entry<String, String> entry : osoby.entrySet()) {
      System.out.println(entry);
    }
  }

  private static void test6() {
    Set<String> napisy = new TreeSet<>();
    napisy.add("bbb");
    napisy.add("aaa");
    napisy.add("ccc");
    napisy.add("aaa");
    napisy.add("acpi");

    Set<Point2D> punkty = new TreeSet<>(new Comparator<Point2D>() {
      @Override
      public int compare(Point2D o1, Point2D o2) {
        double d1 = o1.distanceFromOrigin();
        double d2 = o2.distanceFromOrigin();
        if (d1 < d2)
          return -1;
        if (d1 > d2)
          return 1;
        return 0;
      }
    });

    System.out.println(napisy);

    punkty.add(new Point2D(1, 2));
    punkty.add(new Point2D(3, 4));
    punkty.add(new Point2D(-3, 2));
    punkty.add(new Point2D(3, 4));

    System.out.println(punkty);
  }

  private static void test5() {
    Timeit.printingElapsedTime(new Runnable() {
      @Override
      public void run() {
        Queue<Integer> queue = new QueueImpl<>();

        for (int i = 0; i < 1_000_000; i++) {
          queue.put(i);
        }

        for (int i = 0; i < 1_000_000; i++) {
          queue.remove();
        }
      }
    });
  }

  private static void test4() {
    int N = 1_000_000;
    List<Integer> nums = randomArrayList(N);

    long start = System.nanoTime();
    long sum = 0;

    for (Integer n : nums) {
      sum += n;
    }

    long end = System.nanoTime();
    System.out.println((end - start) / 1_000_000.0);
    System.out.println(sum);
  }

  private static List<Integer> randomLinkedList(int N) {
    List<Integer> nums = new LinkedList<>();
    Random rnd = new Random();
    for (int i = 0; i < N; i++) {
      nums.add(rnd.nextInt(10));
    }
    return nums;
  }

  private static List<Integer> randomArrayList(int N) {
    List<Integer> nums = new ArrayList<>(N);
    Random rnd = new Random();
    for (int i = 0; i < N; i++) {
      nums.add(rnd.nextInt(10));
    }
    return nums;
  }

  private static void test3() {
    Ref<String> obj = new Ref<String>("abc");

    Ref obj1 = new Ref(123);
    obj1.set("xyz");
  }

  private static void test2() {
    String[] tablica = {
        "abc", "xyz", "qwerty"
    };
    arrayPut(tablica, 0, 123);
  }

  public static void arrayPut(Object[] array, int i, Object value) {
    array[i] = value;
  }

  private static void test1() {
    Ref<Double> obj = new Ref<Double>(1.25);

    Integer i = 1;
    obj.set(i.doubleValue());

    obj.set(123.4);

    System.out.println(obj.get().getClass());

    double x = obj.get();
  }

}
