// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.singleton;

public final class Singleton {

  private static final Singleton INSTANCE = new Singleton();

  public static Singleton getInstance() {
    return INSTANCE;
  }

  private Singleton() {
    System.out.println("Singleton::new()");
  }

  public static void foo() {
    System.out.println("Singleton::foo()");
  }
}
