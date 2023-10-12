package edu.san;

public final class Singleton {

  private static final Singleton INSTANCE = new Singleton();

  public static Singleton getInstance() {
    return INSTANCE;
  }

  public static void foo() {
    System.out.println("Singleton::foo()");
  }

  private Singleton() {
    System.out.println("Singleton::new()");
  }
}
