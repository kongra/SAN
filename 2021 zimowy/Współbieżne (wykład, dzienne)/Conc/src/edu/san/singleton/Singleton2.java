package edu.san.singleton;

public enum Singleton2 {
  INSTANCE;

  private final String name;

  Singleton2() {
    name = "A highly vulnerable content";
    System.out.println("Działa Singleton2()");
  }

  public void foo() {
    System.out.println("foo() running");
  }
}
