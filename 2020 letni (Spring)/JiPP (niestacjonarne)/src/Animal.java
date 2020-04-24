public interface Animal {

  void eat(Food food);

  void sleep();

  default void runLifeCycle(Food defaultFood) {
    while(true) {
      eat(defaultFood);
      sleepActually(1000);
      sleep();
      sleepActually(1000);
    }
  }

  default void sleepActually(long msecs) {
    try {
      Thread.sleep(msecs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
