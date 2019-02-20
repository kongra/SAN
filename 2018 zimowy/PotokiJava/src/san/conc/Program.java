package san.conc;

public class Program {

  public static void main(String... args) {
    System.out.println(Thread.currentThread());

    Task1 t1 = new Task1();

    System.out.println("Utworzyłem wątek");
    Thread th1 = new Thread(t1);
    th1.start();

    Runnable t2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("t2 działa w wątku " + Thread.currentThread());
      }
    };
    new Thread(t2).start();
    System.out.println(t2.getClass());

    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("t3 działa w wątku " + Thread.currentThread());
      }
    }).start();

    Runnable t4 = () -> {
      System.out.println("t4 działa w wątku " + Thread.currentThread());
    };
    new Thread(t4).start();

    new Thread(() -> {
      System.out.println("t5 działa w wątku " + Thread.currentThread());
    }).start();

    System.out.println("Uruchomiłem wątek. KONIEC.");
  }

}
