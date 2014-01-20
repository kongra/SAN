package san.lect04;

public class Lect04 {

  static void foo() {
    System.out.println("Działa foo()");
    try {
      Thread.sleep(500);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ResourcePool<Integer> pool = new ResourcePool<>(1, 2, 3, 4, 5);
    for (int i = 0; i < 10; i++) {
      new Thread(new ResourceUser<Integer>("RU" + i, Integer.MAX_VALUE, 2000,
          pool)).start();
    }
  }

  private static void test01() {
    Stoppable task = new Stoppable(new Runnable() {
      @Override
      public void run() {
        foo();
      }
    });

    new Thread(task).start();

    try {
      Thread.sleep(3000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    task.stop();

    System.out.println("Skończone");
  }

}
