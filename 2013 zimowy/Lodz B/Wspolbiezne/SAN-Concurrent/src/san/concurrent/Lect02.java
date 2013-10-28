package san.concurrent;

public class Lect02 {

  public static void main(String[] args) {
    final Object monitor1 = new Object();
    final Object monitor2 = new Object();
    
    Runnable work1 = new Runnable(){
      @Override
      public void run() {
        synchronized(monitor1) {
          try {
            Thread.sleep(200);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          
          System.out.println("work1: działa");
          synchronized(monitor2) {
            try {
              Thread.sleep(200);
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    };
    
    Runnable work2 = new Runnable(){
      @Override
      public void run() {
        synchronized(monitor2) {
          try {
            Thread.sleep(200);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          
          System.out.println("work2: działa");
          synchronized(monitor1) {
            try {
              Thread.sleep(200);
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    };
    
    new Thread(work1).start();
    new Thread(work2).start();
    
    System.out.println("main: koniec");
    
    // test1();
    // test2();
    // test3();
    // test4();
  }

  private static void test4() {
    Stoppable work1 = new Stoppable() {
      @Override
      public void run() {
        while (!isStopped()) {
          System.out.println("work1: działa");
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    new Thread(work1).start();

    try {
      Thread.sleep(200);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    work1.stop();

    System.out.println("main: koniec");
  }

  private static void test3() {
    final Object obj = new Object();

    Runnable work1 = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println("work1: działa");
          if (i == 4) {
            synchronized (obj) {
              obj.notifyAll();
            }
          }
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    Thread t = new Thread(work1);
    t.start();

    try {
      synchronized (obj) {
        obj.wait();
      }
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("main: koniec");
  }

  private static void test2() {
    Runnable work1 = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println("work1: działa");
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    Thread t = new Thread(work1);
    t.start();

    try {
      t.join();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    new Thread(work1).start();

    System.out.println("main: koniec");
  }

  private static void test1() {
    Runnable work1 = new Runnable() {
      @Override
      public void run() {
        for (;;) {
          System.out.println("work1: działa");
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    Thread t = new Thread(work1);
    t.setDaemon(true);
    t.start();

    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("main: koniec");
  }

}
