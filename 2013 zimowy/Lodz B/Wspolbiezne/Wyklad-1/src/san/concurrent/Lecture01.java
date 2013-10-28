package san.concurrent;

public class Lecture01 {

  private static void runInThread(final String txt, final int repeats) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < repeats; i++) {
          System.out.println(txt + ": " + i);
          try {
            Thread.sleep(10);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }

  public static void main(String[] args) {
    for (int i = 0; i < 1; i++) {
      runInThread("Robota A", 10);
      runInThread("Robota B", 7);
    }

  }

  private static void test1() {
    Runnable todo = new Runnable() {
      @Override
      public void run() {
        System.out.println("Działa nowy wątek");
      }
    };

    Thread t1 = new Thread(todo);
    t1.start();

    System.out.println("Hello!!");
  }

}
