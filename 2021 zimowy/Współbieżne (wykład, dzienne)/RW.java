class RW {

  static void reader() {
    while (true) {
      sleep();
      enter();
      read(); // takes ~100ms
      exit();
    }
  }

  static void writer() {
    while (true) {
      sleep();
      enter();
      write(); // takes ~100ms
      exit();
    }
  }

  public static void main(String... args) {
    Thread r1 = new Thread(() -> {
        reader();
    });
    Thread r2 = new Thread(() -> {
        reader();
    });
    Thread r3 = new Thread(() -> {
        reader();
    });
    Thread w1 = new Thread(() -> {
        writer();
    });
    Thread w2 = new Thread(() -> {
        writer();
    });

    r1.start();
    r2.start();
    r3.start();

    w1.start();
    w2.start();
  }

}
