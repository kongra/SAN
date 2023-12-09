// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.singleton;

public final class SingletonOnDemand {

  private static SingletonOnDemand instance;

  public static SingletonOnDemand getInstance() {
    if (null == instance) {
      instance = new SingletonOnDemand();
    }
    return instance;
  }

  private SingletonOnDemand() {
    System.out.println("SingletonOnDemand::new()");
  }

  public static void goo() {
    System.out.println("SingletonOnDemand::goo()");
  }

}
