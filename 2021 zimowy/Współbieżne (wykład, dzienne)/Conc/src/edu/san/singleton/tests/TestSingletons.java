package edu.san.singleton.tests;

import edu.san.Threads;
import edu.san.singleton.Singleton3;

public class TestSingletons {

  public static void main(String... args) {
    // Singleton1 s1 = new Singleton1();
    // Singleton1 s2 = new Singleton1();

    // Singleton1.INSTANCE.foo();
    // Singleton2.INSTANCE.foo();

    //    System.out.println("TestSingletons.main()");
    //    Singleton1.test();
    //
    //    Threads.sleep(3_000);
    //    Singleton1.getInstance().foo();

    System.out.println("TestSingletons.main()");
    Singleton3.test();

    Threads.sleep(3_000);
    Singleton3.getInstance().foo();
  }

}
