package edu.san.singleton;

public final class Singleton1 {

  public static synchronized Singleton1 getInstance() {
    if (null == INSTANCE) {
      INSTANCE = new Singleton1();
    }
    return INSTANCE;
  }

  private static Singleton1 INSTANCE;

  private final String name;

  private Singleton1() {
    name = "A highly vulnerable content";
    System.out.println("Działa Singleton1()");
  }

  public void foo() {
    System.out.println("foo() running");
  }

  public static void test() {
    System.out.println("Działa Singleton1.test()");
  }
}
