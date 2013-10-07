package san.concurrent;

public class Lecture01 {

  public static void main(String[] args) {
    test1();
  }

  private static void test1() {
    Runnable work = new Runnable() {
      @Override
      public void run() {
        System.out.println("Wykonuję pracę!!!");

      }
    };

    Thread t1 = new Thread(work);
    t1.start();
    System.out.println("Hello World!!!");
  }

}
