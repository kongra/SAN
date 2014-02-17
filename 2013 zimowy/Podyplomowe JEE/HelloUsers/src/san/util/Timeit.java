package san.util;

public class Timeit {

  public static void printingElapsedTime(Runnable task) {
    long start = System.nanoTime();
    try {
      task.run();
    }
    finally {
      long end = System.nanoTime();
      System.out.println("Finished in " + ((end - start) / 1_000_000.0)
          + " msecs.");
    }
  }

  private Timeit() {
    ;
  }

}
