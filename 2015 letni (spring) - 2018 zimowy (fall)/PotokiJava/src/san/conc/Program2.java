package san.conc;

public class Program2 {

  public static void main(String... args) {
    Thread t0 = new Thread(() -> {
      System.out.println("Idę spać: " + Thread.currentThread());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Zakończyłem: " + Thread.currentThread());
    });
    t0.start();

    try {
      System.out.println("Zaczynam czekać na t0: " + Thread.currentThread());
      t0.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("KONIEC: " + Thread.currentThread());

  }

}
