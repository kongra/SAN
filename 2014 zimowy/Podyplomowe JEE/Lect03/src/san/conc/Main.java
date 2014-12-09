package san.conc;

public class Main {

  public static void main(String... args) {
    System.out.println("Startuje zadanie główne");

    final Object synchronizer = new Object();
    
    Runnable task1 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Startuje zadanie 1");
        try {
          // Thread.sleep(1000);
          synchronizer.wait();
          System.out.println("Zakończyło się zadanie 1");
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Runnable task2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Startuje zadanie 2");
        try {
          // Thread.sleep(2000);
          synchronizer.wait();
          System.out.println("Zakończyło się zadanie 2");
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    Thread thread1 = new Thread(task1);
    Thread thread2 = new Thread(task2);
    
    thread1.start();
    thread2.start();

    synchronizer.notifyAll();
    
//    try {
//      thread1.join();
//      thread2.join();
//    }
//    catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    System.out.println("Zakończyło się zadanie główne");
  }
}
