package edu.san.singleton;

public final class Singleton3 {

  public static Singleton3 getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private final String name;

  private Singleton3() {
    name = "A highly vulnerable content";
    System.out.println("Działa Singleton3()");
  }

  public void foo() {
    System.out.println("foo() running");
  }

  public static void test() {
    System.out.println("Działa Singleton3.test()");
  }

  private static class InstanceHolder {

    static Singleton3 INSTANCE = new Singleton3();

  }
}
