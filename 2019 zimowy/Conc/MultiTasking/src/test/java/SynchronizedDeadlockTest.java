import org.junit.jupiter.api.Test;

class SynchronizedDeadlockTest {

  @Test
  void call() {
    SynchronizedDeadlock<String> s1 = new SynchronizedDeadlock<>();
    SynchronizedDeadlock<String> s2 = new SynchronizedDeadlock<>();

    new Thread(() -> {
      s1.call(() -> {
        Thread.sleep(100);
        String r = s2.getMostRecentCallValue();
        Thread.sleep(100);
        return r;
      });
    }).start();

    new Thread(() -> {
      s2.call(() -> {
        Thread.sleep(100);
        String r = s1.getMostRecentCallValue();
        Thread.sleep(100);
        return r;
      });
    }).start();

  }
}
