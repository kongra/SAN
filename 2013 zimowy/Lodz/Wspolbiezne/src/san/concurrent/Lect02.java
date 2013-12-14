package san.concurrent;

public class Lect02 {

  public static void main(String[] args) {
    Stoppable task = new Stoppable() {
      @Override
      public void run() {
        while (!isStopped()) {
          System.out.println("Działam");
          Th.sleep(500);
        }
      }
    };
    
    new Thread(task).start();
    Th.sleep(3000);
    task.stop();
  }

}
