// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

public class Singleton {

  private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton();
  }

//   private static Singleton INSTANCE;

//  public static Singleton getInstance() {
//    if (INSTANCE == null) {
//      INSTANCE = new Singleton();
//    }
//    return INSTANCE;
//  }

  public static Singleton getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public static void test1() {
    System.out.println("Działa test1()");
  }

  private Singleton() {
    System.out.println("Działa Singleton()");
  }

  @SuppressWarnings("static-method")
  public void draw() {
    System.out.println("Działa Singleton.draw()");
  }

}
