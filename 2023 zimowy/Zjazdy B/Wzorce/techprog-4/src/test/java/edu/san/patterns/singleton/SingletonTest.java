// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.singleton;

import org.junit.jupiter.api.Test;

class SingletonTest {

  @Test
  void test() {
    Singleton.foo();
    SingletonOnDemand.goo();
    
    // final var singleton = Singleton.getInstance();
    // final var singleton2 = Singleton.getInstance();
    // assertThat(singleton).isEqualTo(singleton2);
  }

}
