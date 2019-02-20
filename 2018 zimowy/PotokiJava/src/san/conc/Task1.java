package san.conc;

public class Task1 implements Runnable {

  @Override
  public void run() {
    System.out.println("Task1 działa w wątku " + Thread.currentThread());
  }

}
