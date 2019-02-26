package san.conc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Program1 {

  public static void main(String... args) {
    ExecutorService pool = Executors.newFixedThreadPool(100);

//    for (int i  = 0; i < 100; i++) {
//      final int j = i;
//      pool.execute(() -> {
//        System.out.println("Działa wątek " + j + Thread.currentThread());
//      });
//    }

    final Random rnd = new Random();
    double sum = 0;

    List<Future<Double>> results = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      final double x = i;
      Future<Double> result = pool.submit(() -> {
        long msecs = rnd.nextInt(2000);
        System.out.println("Wątek " + x + Thread.currentThread() + " idzie spać na " + msecs);
        Thread.sleep(msecs);
        return x + 4.0;
      });
      results.add(result);
    }

    for (Future<Double> f : results) {
      try {
        sum += f.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

    System.out.println("Suma wynosi " + sum);

  }

}
