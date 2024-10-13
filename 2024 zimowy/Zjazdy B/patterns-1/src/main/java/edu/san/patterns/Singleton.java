// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

public class Singleton {

  private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton();
  }

  public static Singleton getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private Singleton() {
    System.out.println("Działa Singleton()");
  }

}
