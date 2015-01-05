package san.conc04;

import san.conc03.Threads;

public class TestStoppable {

  static class Task extends Stoppable {
    @Override
    public void run() {
      while (isRunning()) {
        System.out.println("Działam sobie ...");
        Threads.sleep(1000);
      }
    }
  }

  public static void main(String[] args) {
    Task t = new Task();
    new Thread(t).start();

    Threads.sleep(4000);
    t.stop();
  }

}
